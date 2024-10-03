package com.jamescoggan.sample.recipes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jamescoggan.sample.di.AppScope
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
class RecipesScreen : Screen {
    data class State(
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object OnSend : Event
        data object OnReceive : Event
    }
}

class RecipePresenter @AssistedInject constructor(
    @Assisted private val screen: RecipesScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<RecipesScreen.State> {
    @Composable
    override fun present(): RecipesScreen.State {
        return RecipesScreen.State { event ->
            when (event) {
                RecipesScreen.Event.OnReceive ->
                    TODO()

                RecipesScreen.Event.OnSend -> TODO()
            }
        }
    }

    @CircuitInject(RecipesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: RecipesScreen, navigator: Navigator): RecipePresenter
    }
}

@CircuitInject(screen = RecipesScreen::class, scope = AppScope::class)
@Composable
fun RecipeContent(state: RecipesScreen.State, modifier: Modifier = Modifier) {
    Text("Recipe screen")
}