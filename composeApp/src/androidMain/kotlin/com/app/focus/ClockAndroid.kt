package com.app.focus

import ClockComponents
import Timer
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.app.focus.notifications.makeTimerNotification

@Composable
fun ClockAndroid() {
    val context = LocalContext.current
    val appName = stringResource(id = R.string.app_name)
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
    Button(onClick = {
        makeTimerNotification(
            appName,
            "Button pushed",
            context
        )
    }) {
        Text("Press for notification")
    }
}

