package com.jamescoggan.sample.recipes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jamescoggan.sample.R
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.testingData.recommendedRecipesSamples
import com.jamescoggan.sample.ui.Base64HtmlView
import com.jamescoggan.sample.ui.models.RecipeUiItem
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize


@Parcelize
data class RecipeDetailsScreen(val recipeId: Int) : Screen {
    data class State(val recipe: RecipeUiItem) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object GoBack : Event
    }
}

class RecipeDetailsPresenter @AssistedInject constructor(
    @Assisted private val screen: RecipeDetailsScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<RecipeDetailsScreen.State> {
    @Composable
    override fun present(): RecipeDetailsScreen.State {
        // TODO: get from datasource using ID
        return RecipeDetailsScreen.State(
            recommendedRecipesSamples[0]
        )
    }

    @CircuitInject(RecipeDetailsScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: RecipeDetailsScreen, navigator: Navigator): RecipeDetailsPresenter
    }
}

@CircuitInject(screen = RecipeDetailsScreen::class, scope = AppScope::class)
@Composable
fun RecipeDetailsContent(state: RecipeDetailsScreen.State, modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        state.recipe.title,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    model = state.recipe.imageUrl,
                    contentDescription = state.recipe.title,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(R.drawable.recipe_placeholder)
                )
                Base64HtmlView(state.recipe.cookingSteps)
            }
        }
    }
}

@Preview
@Composable
fun RecipeDetailsScreenPreview() {
    AndroidAppSampleTheme {
        RecipeDetailsContent(
            RecipeDetailsScreen.State(
                recommendedRecipesSamples[0]
            )
        )
    }
}