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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.sp
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.ui.models.RecipeCategory
import com.jamescoggan.sample.ui.models.RecipeUiItem
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
import kotlinx.parcelize.Parcelize

@Parcelize
class RecipesScreen : Screen {
    sealed interface State : CircuitUiState {

        data object Loading : State

        data class Error(val message: String) : State

        data class Success(
            val recipes: ImmutableList<RecipeUiItem>,
            val isRefreshing: Boolean,
            val eventSink: (Event) -> Unit = {}
        ) :
            State

    }

    sealed interface Event : CircuitUiEvent {
        data object Refresh : Event
        data class OnRecipeSelected(val recipeUiItem: RecipeUiItem) : Event
    }
}

class RecipePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<RecipesScreen.State> {
    @Composable
    override fun present(): RecipesScreen.State {
        var isRefreshing by remember { mutableStateOf(false) }
        if (isRefreshing) {
            LaunchedEffect(Unit) {
                // repository refresh
                isRefreshing = false
            }
        }

        val recipesState by remember {
            mutableStateOf(
                persistentListOf(
                    RecipeUiItem(
                        id = 0,
                        title = "Chicken chow mei",
                        imageUrl = "https://www.cookingclassy.com/wp-content/uploads/2019/01/chow-mein-4.jpg",
                        category = RecipeCategory.CHINESE
                    ), RecipeUiItem(
                        id = 1,
                        title = "Spanish Paella",
                        imageUrl = "https://www.jocooks.com/wp-content/uploads/2018/12/paella-1.jpg",
                        category = RecipeCategory.SPANISH
                    ), RecipeUiItem(
                        id = 2,
                        title = "Butter chicken",
                        imageUrl = "https://www.simplyrecipes.com/thmb/1SXZ_F1GC6ww_ppWnrdbKgHi9fQ=/2000x1333/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__01__Butter-Chicken-LEAD-2-6ca76f24bbe74114a09958073cb9c76f.jpg",
                        category = RecipeCategory.INDIAN
                    )
                )
            )
        }

        var errorMessage by remember { mutableStateOf<String?>(null) }

        return when {
            errorMessage != null -> {
                RecipesScreen.State.Error(errorMessage!!)
            }

            isRefreshing -> RecipesScreen.State.Loading
            else -> RecipesScreen.State.Success(recipesState, isRefreshing) { event ->
                when (event) {
                    is RecipesScreen.Event.OnRecipeSelected -> TODO() // navigator.goto()
                    RecipesScreen.Event.Refresh -> isRefreshing = true
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
                        "Adoptables",
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors =
                TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        when (state) {
            RecipesScreen.State.Loading ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

            is RecipesScreen.State.Error ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(modifier = Modifier, text = state.message)
                }

            is RecipesScreen.State.Success ->
                RecipeListGrid(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    recipesList = state.recipes,
                    isRefreshing = state.isRefreshing,
                    onItemClick = { state.eventSink(RecipesScreen.Event.OnRecipeSelected(it)) },
                    onRefresh = { state.eventSink(RecipesScreen.Event.Refresh) }
                )
        }
    }
}