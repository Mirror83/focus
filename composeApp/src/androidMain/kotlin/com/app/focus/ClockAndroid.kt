package com.app.focus

import ClockComponents
import Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun ClockAndroid() {
    val timer = remember {
        Timer(1)
    }

    if (!timer.isPaused() && timer.isStarted()) {
        LaunchedEffect(timer) {
            timer.tick()
            timer.stop()
        }
    }
    ClockComponents(timer = timer)
}