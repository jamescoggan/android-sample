package com.jamescoggan.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Todo inject with dagger
        val circuit: Circuit =
            Circuit.Builder()
                .addPresenterFactory(buildPresenterFactory())
                .addUiFactory(buildUiFactory())
                .build()

        setContent {
            AndroidAppSampleTheme {
                CircuitCompositionLocals(circuit) { CircuitContent(HomeScreen) }
            }
        }
    }
}

private fun buildPresenterFactory(): Presenter.Factory =
    Presenter.Factory { _, _, _ ->
        HomePresenter()
    }

private fun buildUiFactory(): Ui.Factory =
    Ui.Factory { _, _ ->
        ui<HomeScreen.State> { state, modifier -> HomeUi(state, modifier) }
    }