import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onTimerClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
    onStatsClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Focus", style = MaterialTheme.typography.h2)
        Column {
            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { onTimerClicked() }) {
                Text("Timer")
            }
            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { onStatsClicked() }) {
                Text("Stats")
            }
            Spacer(Modifier.height(8.dp))

            TextButton(onClick = { onSettingsClicked() }) {
                Text("Settings")
            }
        }

    }
}