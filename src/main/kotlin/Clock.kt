import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun Clock() {
    val timer = remember {
        Timer()
    }

    if (!timer.isPaused() && timer.isStarted()) {
        LaunchedEffect(timer) {
            timer.tick()
            timer.stop()
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
            Text(timer.output(), fontSize = 30.sp)
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