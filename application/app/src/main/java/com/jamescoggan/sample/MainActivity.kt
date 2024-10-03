package com.jamescoggan.sample

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.jamescoggan.sample.di.ActivityKey
import com.jamescoggan.sample.di.AppScope
import com.jamescoggan.sample.ui.theme.AndroidAppSampleTheme
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitContent
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, boundType = Activity::class)
@ActivityKey(MainActivity::class)
class MainActivity @Inject constructor(private val circuit: Circuit) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidAppSampleTheme {
                CircuitCompositionLocals(circuit) { CircuitContent(HomeScreen()) }
            }
        }
    }
}
