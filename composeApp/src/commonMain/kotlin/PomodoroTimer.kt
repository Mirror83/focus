import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class PomodoroTimer(workMinutes: Int = 25, breakMinutes: Int = 5, longBreakMinutes: Int = 15) {
    private val workSeconds = workMinutes * 60
    private val breakSeconds = breakMinutes * 60
    private val longBreakSeconds = longBreakMinutes * 60

    var isRunning by mutableStateOf(false)
        private set

    private var remainingSeconds by mutableStateOf(workSeconds)

    private var pomodoroState by mutableStateOf(PomodoroState.START)
    var completedSessions by mutableStateOf(0)
        private set

    suspend fun tick() {
        when (pomodoroState) {
            PomodoroState.WORK, PomodoroState.SHORT_BREAK, PomodoroState.LONG_BREAK -> {
                while (remainingSeconds > 0) {
                    if (isPaused()) return

                    delay(1_000)
                    remainingSeconds -= 1
                }

                updateState()
            }

            else -> {}
        }

    }

    fun isStarted() = pomodoroState != PomodoroState.START

    fun isPaused() = !isRunning

    fun hasBreakStarted(): Boolean {
        return when(pomodoroState) {
            PomodoroState.SHORT_BREAK -> remainingSeconds < breakSeconds
            PomodoroState.LONG_BREAK -> remainingSeconds < longBreakSeconds
            else -> false
        }
    }

//    fun hasWorkStarted() = pomodoroState == PomodoroState.WORK && remainingSeconds < workSeconds

    fun isOnBreak() =
        pomodoroState == PomodoroState.SHORT_BREAK || pomodoroState == PomodoroState.LONG_BREAK

    fun isOnLongBreak() = pomodoroState == PomodoroState.LONG_BREAK

    fun pause() {
        isRunning = false
    }

    fun resume() {
        isRunning = true
    }

    fun start() {
        pomodoroState = PomodoroState.WORK
        isRunning = true
    }

    fun reset() {
        pomodoroState = PomodoroState.START
        isRunning = false
        remainingSeconds = workSeconds
        completedSessions = 0
    }


    /** Called after the timer finishes ticking **/
    private fun updateState() {
        isRunning = false

        if (
            pomodoroState == PomodoroState.SHORT_BREAK ||
            pomodoroState == PomodoroState.LONG_BREAK
        ) {
            if (pomodoroState == PomodoroState.LONG_BREAK) completedSessions = 0
            pomodoroState = PomodoroState.WORK
            remainingSeconds = workSeconds
        } else if (pomodoroState == PomodoroState.WORK) {
            completedSessions += 1

            if (completedSessions % 4 != 0) {
                pomodoroState = PomodoroState.SHORT_BREAK
                remainingSeconds = breakSeconds
            } else {
                pomodoroState = PomodoroState.LONG_BREAK
                remainingSeconds = longBreakSeconds
            }
        }
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

enum class PomodoroState {
    START,
    WORK,
    SHORT_BREAK,
    LONG_BREAK
}