import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
//import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp

@Composable
fun ClockComponents(timer: PomodoroTimer) {
    val incompleteColor by animateColorAsState(MaterialTheme.colorScheme.secondary)
    val completeColor by animateColorAsState(MaterialTheme.colorScheme.tertiary)

    Column {
        Row(modifier = Modifier.padding(32.dp)) {
            repeat(4) {
                val currentCircle = it + 1

                Box(modifier = Modifier.drawBehind {
                    drawCircle(
                        if ((currentCircle - timer.completedSessions) > 0)
                            incompleteColor else completeColor,
                        10.dp.toPx(),
                        center = Offset(center.x + it * 30.dp.toPx(), center.y)
                    )
                })
            }
        }


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Text(
                    text =
                    if (timer.isOnBreak()) {
                        if (timer.isOnLongBreak()) "Long Break" else "Short Break"
                    } else "Work",
                    style = MaterialTheme.typography.titleMedium

                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(timer.output(), style = MaterialTheme.typography.displayLarge)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                AnimatedVisibility(!timer.isRunning) {
                    OutlinedButton(onClick = {
                        if (timer.isStarted()) {
                            timer.reset()
                        } else {
                            timer.start()
                        }
                    }) {
                        Text(if (timer.isStarted()) "Reset" else "Start")
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                AnimatedVisibility(timer.isStarted()) {
                    ElevatedButton(onClick = {
                        if (!timer.isPaused())
                            timer.pause()
                        else timer.resume()
                    }) {
                        Text(
                            text = if (timer.isPaused()) {
                                if (timer.isOnBreak()) {
                                    if (timer.hasBreakStarted()) "Resume break" else "Start break"
                                } else "Continue working"
                            } else "Pause"
                        )
                    }
                }

            }
        }
    }
}

//@Preview
//@Composable
//fun ClockComponentsPreview() {
//    ClockComponents(timer = PomodoroTimer())
//}