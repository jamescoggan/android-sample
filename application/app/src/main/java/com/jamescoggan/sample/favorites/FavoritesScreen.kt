package com.jamescoggan.sample.favorites

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
class FavoritesScreen : Screen {
    data class State(
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object OnSend : Event
        data object OnReceive : Event
    }
}

class FavoritesPresenter @AssistedInject constructor(
    @Assisted private val screen: FavoritesScreen,
    @Assisted private val navigator: Navigator,
) : Presenter<FavoritesScreen.State> {
    @Composable
    override fun present(): FavoritesScreen.State {
        return FavoritesScreen.State { event ->
            when (event) {
                FavoritesScreen.Event.OnReceive ->
                    TODO()

                FavoritesScreen.Event.OnSend -> TODO()
            }
        }
    }

    @CircuitInject(FavoritesScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(screen: FavoritesScreen, navigator: Navigator): FavoritesPresenter
    }
}

@CircuitInject(screen = FavoritesScreen::class, scope = AppScope::class)
@Composable
fun FavoritesContent(state: FavoritesScreen.State, modifier: Modifier = Modifier) {
    Text("Favorites")
}