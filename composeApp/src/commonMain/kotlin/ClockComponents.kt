import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClockComponents(timer: Timer) {
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

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(onClick = {
                if (timer.isStarted()) {
                    timer.reset()
                } else {
                    timer.start()
                }
            }) {
                Text(if (timer.isStarted()) "Stop" else "Start")
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