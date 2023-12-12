import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ClockComponents(timer: PomodoroTimer) {
    val incompleteColor by animateColorAsState(Color.Gray)
    val completeColor by animateColorAsState(MaterialTheme.colors.secondary)

    Column {
        Row(modifier = Modifier.padding(32.dp)) {
            repeat(4) {
                val currentCircle = it + 1

                Box(modifier = Modifier.drawBehind {
                    drawCircle(
                        if ((currentCircle - timer.completedSessions) > 0)
                            incompleteColor else completeColor,
                        10.dp.toPx(),
                        center = Offset(center.x + it * 30, center.y)
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
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(timer.output(), style = MaterialTheme.typography.h2)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedButton(onClick = {
                    if (timer.isStarted()) {
                        timer.reset()
                    } else {
                        timer.start()
                    }
                }) {
                    Text(if (timer.isRunning || timer.isStarted()) "Reset" else "Start")
                }

                Spacer(modifier = Modifier.width(8.dp))

                AnimatedVisibility(timer.isStarted()) {
                    Button(onClick = {
                        if (!timer.isPaused())
                            timer.pause()
                        else timer.resume()
                    }) {
                        Text(if (timer.isPaused()) "Resume" else "Pause")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun ClockComponentsPreview() {
    ClockComponents(timer = PomodoroTimer())
}