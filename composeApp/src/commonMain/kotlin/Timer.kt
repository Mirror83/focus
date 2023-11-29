import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class Timer(minutes: Int = 1) {
    private val totalSeconds = minutes * 60
    private var elapsedSeconds by mutableStateOf(0)

    private var started by mutableStateOf(false)
    private var paused by mutableStateOf(false)

    suspend fun tick() {
        while (elapsedSeconds < totalSeconds) {
            delay(1_000)
            elapsedSeconds += 1
            println(elapsedSeconds)
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
        elapsedSeconds = 0
    }

    fun stop() {
        started = false
        paused = true
    }

    fun start() {
        elapsedSeconds = 0
        started = true
        paused = false
    }

    fun output(): String {
        val minutes = elapsedSeconds / 60
        val remainingSeconds = elapsedSeconds % 60
        return "${formatTime(minutes)}:${formatTime(remainingSeconds)}"
    }

    private fun formatTime(time: Int): String {
        return if (time < 10)
            "0$time"
        else "$time"
    }
}