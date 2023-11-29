import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.TrayState
import androidx.compose.ui.window.rememberNotification


@Composable
@Preview
fun ClockDesktop(trayState: TrayState) {
    val pomodoroDoneNotification =
        rememberNotification("Focus", "Pomodoro ended")

    val timer = remember {
        Timer()
    }


    if (!timer.isPaused() && timer.isStarted()) {
        LaunchedEffect(timer) {
            timer.tick()
            timer.stop()
            println("Send notification")

            trayState.sendNotification(pomodoroDoneNotification)

        }
    }

    ClockComponents(timer)
}