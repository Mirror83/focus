import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class Timer(durationInMinutes: Int = 1) {
    private val durationInSeconds = durationInMinutes * 60
    private var remainingSeconds by mutableStateOf(durationInSeconds)

    private var started by mutableStateOf(false)
    private var paused by mutableStateOf(false)

    suspend fun tick() {
        while (remainingSeconds > 0) {
            delay(1_000)
            remainingSeconds -= 1
        }
    }

    fun pause() {
        if (!paused) paused = true
    }

    fun resume() {
        if (paused) paused = false
    }
    fun isPaused() = paused

    fun isStarted() = started

    fun reset() {
        started = false
        paused = true
        remainingSeconds = durationInSeconds
    }

    fun stop() {
        started = false
        paused = true
    }

    fun start() {
        remainingSeconds = durationInSeconds
        started = true
        paused = false
    }

    fun output(): String {
        val minutes = remainingSeconds / 60
        val remainingSeconds = remainingSeconds % 60
        return "${formatTime(minutes)}:${formatTime(remainingSeconds)}"
    }

    private fun formatTime(time: Int): String {
        return if (time < 10)
            "0$time"
        else "$time"
    }
}