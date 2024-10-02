package com.jamescoggan.sample

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize


@Parcelize
data object HomeScreen : Screen {
    data class State(
        val eventSink: (Event) -> Unit,
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data object OnSend : Event
        data object OnReceive : Event
    }
}

internal class HomePresenter(
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        return HomeScreen.State() { event ->
            when (event) {
                HomeScreen.Event.OnReceive -> TODO()
                HomeScreen.Event.OnSend -> TODO()
            }
        }
    }
}

@Composable
internal fun HomeUi(state: HomeScreen.State, modifier: Modifier = Modifier) {
    Text("Hello world")
}