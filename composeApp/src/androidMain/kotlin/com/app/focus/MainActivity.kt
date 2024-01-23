package com.app.focus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.focus.notifications.createTimerNotificationChannel
import com.app.focus.ui.theme.FocusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // One must create the notification channel before posting
        // any notifications.
        createTimerNotificationChannel(this)

        setContent {
            FocusTheme {
                FocusApp()
            }
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    ClockAndroid()
}