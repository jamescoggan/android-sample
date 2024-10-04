package com.jamescoggan.sample

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.navigation.BottomNavItem
import com.jamescoggan.sample.navigation.BottomNavigationBar
import com.jamescoggan.sample.navigation.NAV_ITEMS
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.foundation.NavEvent
import com.slack.circuit.foundation.onNavEvent
import com.slack.circuit.retained.rememberRetained
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.parcelize.Parcelize

@Parcelize
class HomeScreen : Screen {
    data class State(
        val navItems: ImmutableList<BottomNavItem> = NAV_ITEMS,
        val selectedIndex: Int = 0,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        class ClickNavItem(val index: Int) : Event
        class ChildNav(val navEvent: NavEvent) : Event
    }
}

class HomePresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<HomeScreen.State> {
    @Composable
    override fun present(): HomeScreen.State {
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
        return HomeScreen.State(selectedIndex = selectedIndex) { event ->
            when (event) {
                is HomeScreen.Event.ClickNavItem -> selectedIndex = event.index
                is HomeScreen.Event.ChildNav -> navigator.onNavEvent(event.navEvent)
            }
        }
    }

    @CircuitInject(HomeScreen::class, AppScope::class)
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): HomePresenter
    }
}

@CircuitInject(screen = HomeScreen::class, scope = AppScope::class)
@Composable
fun HomeContent(state: HomeScreen.State, modifier: Modifier = Modifier) {
    var contentComposed by rememberRetained { mutableStateOf(false) }
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        containerColor = Color.Transparent,
        bottomBar = {
            AndroidAppSampleTheme {
                BottomNavigationBar(selectedIndex = state.selectedIndex) { index ->
                    state.eventSink(HomeScreen.Event.ClickNavItem(index))
                }
            }
        },
    ) { paddingValues ->
        contentComposed = true
        val screen = state.navItems[state.selectedIndex].screen
        CircuitContent(
            screen,
            modifier = Modifier.padding(paddingValues),
            onNavEvent = { event -> state.eventSink(HomeScreen.Event.ChildNav(event)) },
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    AndroidAppSampleTheme {
        HomeContent(
            HomeScreen.State(navItems = NAV_ITEMS, selectedIndex = 0) {},
            modifier = Modifier
        )
    }
}