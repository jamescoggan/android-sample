package com.jamescoggan.sample

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jamescoggan.sample.di.ActivityKey
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.overlay.ContentWithOverlays
import com.slack.circuitx.android.AndroidScreen
import com.slack.circuitx.android.IntentScreen
import com.slack.circuitx.android.rememberAndroidScreenAwareNavigator
import com.squareup.anvil.annotations.ContributesMultibinding
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(MainActivity::class)
class MainActivity @Inject constructor(private val circuit: Circuit) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val initialBackstack = persistentListOf(HomeScreen())

        setContent {
            AndroidAppSampleTheme {
                val backStack = rememberSaveableBackStack(initialBackstack)
                val circuitNavigator = rememberCircuitNavigator(backStack)
                val navigator = rememberAndroidScreenAwareNavigator(circuitNavigator, this::goTo)

                CircuitCompositionLocals(circuit) {
                    ContentWithOverlays {
                        NavigableCircuitContent(
                            navigator = navigator,
                            backStack = backStack,
                        )
                    }
                }
            }
        }
    }

    private fun goTo(screen: AndroidScreen) = when (screen) {
        is IntentScreen -> screen.startWith(this)
        else -> error("Unknown AndroidScreen: $screen")
    }
}
