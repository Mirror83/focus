package com.app.focus

import ClockComponents
import PomodoroTimer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.app.focus.notifications.makeTimerNotification

@Composable
fun ClockAndroid() {
    val context = LocalContext.current

    val timer = remember {
        PomodoroTimer(2,1,3)
    }

    if (!timer.isPaused() && timer.isStarted()) {
        LaunchedEffect(timer) {
            timer.tick()

            timer.apply {
                if (isStarted() && isOnBreak()) {
                    if (isOnLongBreak())
                        makeTimerNotification(
                        "Four work sessions complete",
                        "Time for a long break. You deserve it!",
                        context
                    ) else
                        makeTimerNotification(
                        "Work session complete",
                        "Time for a quick break",
                        context
                    )
                } else if (isStarted() && !isOnBreak()) {
                    makeTimerNotification(
                        "Break complete",
                        "Time to go back to work",
                        context
                    )
                }
            }
        }
    }

    ClockComponents(timer = timer)

}

