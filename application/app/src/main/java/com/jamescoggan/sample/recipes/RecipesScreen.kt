package com.jamescoggan.sample.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jamescoggan.data.model.Resource
import com.jamescoggan.data.repo.RecipesRepository
import com.jamescoggan.sample.R
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.testingData.recommendedRecipesSamples
import com.jamescoggan.sample.ui.models.RecipeUiItem
import com.jamescoggan.sample.ui.models.toUiItems
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.jamescoggan.sample.ui.toLocalizedString
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
class RecipesScreen : Screen {
    sealed interface State : CircuitUiState {

        data object Refreshing : State

        data class Error(val message: String) : State

        data object Empty : State

        data class Success(
            val recipes: ImmutableList<RecipeUiItem>, val eventSink: (Event) -> Unit = {}
        ) : State

    }

    sealed interface Event : CircuitUiEvent {
        data object Refresh : Event
        data class OnRecipeSelected(val recipeId: Int) : Event
    }
}

class RecipePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator, private val recipesRepository: RecipesRepository
) : Presenter<RecipesScreen.State> {
    @Composable
    override fun present(): RecipesScreen.State {
        val scope = rememberCoroutineScope()
        val context = LocalContext.current

        var isRefreshing by remember { mutableStateOf(true) }

        val recipesState by produceState<ImmutableList<RecipeUiItem>>(
            initialValue = persistentListOf(), recipesRepository
        ) {
            recipesRepository.recommendedFlow().distinctUntilChanged().onEmpty {
                isRefreshing = false
            }.collect {
                isRefreshing = false
                value = it.toUiItems()
            }
        }

        var errorMessage by remember { mutableStateOf<String?>(null) }

        return when {
            isRefreshing -> RecipesScreen.State.Refreshing
            !errorMessage.isNullOrEmpty() -> RecipesScreen.State.Error(errorMessage!!)
            recipesState.isEmpty() -> RecipesScreen.State.Empty
            else -> RecipesScreen.State.Success(recipesState) { event ->
                when (event) {
                    is RecipesScreen.Event.Refresh -> {
                        scope.launch {
                            recipesRepository.refreshRecommended().collect { value ->
                                when (value) {
                                    is Resource.Loading -> {
                                        isRefreshing = true
                                        Timber.e("Loading")
                                    }

                                    is Resource.Error -> {
                                        Timber.e("Error")
                                        errorMessage =
                                            value.resourceError.toLocalizedString(context)
                                        isRefreshing = false
                                    }

                                    is Resource.Success -> {
                                        Timber.e("Success")
                                        isRefreshing = false
                                    }
                                }
                            }
                        }
                    }

                    is RecipesScreen.Event.OnRecipeSelected -> {
                        navigator.goTo(
                            RecipeDetailsScreen(
                                event.recipeId
                            )
                        )
                    }
                }
            }
        }
    }

    @CircuitInject(RecipesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): RecipePresenter
    }
}

@CircuitInject(screen = RecipesScreen::class, scope = AppScope::class)
@Composable
fun RecipeContent(state: RecipesScreen.State, modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Recipes", fontSize = 22.sp, color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        when (state) {
            RecipesScreen.State.Refreshing -> Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            is RecipesScreen.State.Error -> Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(modifier = Modifier, text = state.message)
            }

            is RecipesScreen.State.Empty -> Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(modifier = Modifier, text = stringResource(R.string.no_recipes_available))
            }

            is RecipesScreen.State.Success -> RecipeListGrid(modifier = Modifier
                .padding(
                    paddingValues
                )
                .fillMaxSize(),
                recipesList = state.recipes,
                onItemClick = { state.eventSink(RecipesScreen.Event.OnRecipeSelected(it.id)) },
                onRefresh = { state.eventSink(RecipesScreen.Event.Refresh) })
        }
    }
}

@Preview
@Composable
fun RecipesScreenSuccessPreview() {
    AndroidAppSampleTheme {
        RecipeContent(RecipesScreen.State.Success(
            recommendedRecipesSamples
        ) {})
    }
}

@Preview
@Composable
fun RecipesScreenSuccessError() {
    AndroidAppSampleTheme {
        RecipeContent(
            RecipesScreen.State.Error(
                stringResource(R.string.resource_error_no_connection)
            )
        )
    }
}


@Preview
@Composable
fun RecipesScreenSuccessRefreshing() {
    AndroidAppSampleTheme {
        RecipeContent(RecipesScreen.State.Refreshing)
    }
}


@Preview
@Composable
fun RecipesScreenSuccessEmpty() {
    AndroidAppSampleTheme {
        RecipeContent(RecipesScreen.State.Empty)
    }
}