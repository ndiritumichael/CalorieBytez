package com.devmike.commonui.sharedui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devmike.domain.models.AppErrors

@Composable
fun ErrorScreen(
    error: AppErrors,
    onRetry: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector =
                when (error) {
                    is AppErrors.NoInternet -> Icons.Default.WifiOff
                    is AppErrors.Timeout -> Icons.Default.TimerOff
                    is AppErrors.Unknown -> Icons.Default.Error
                    is AppErrors.NotFound -> Icons.Default.SearchOff
                    is AppErrors.Empty -> Icons.Default.Warning
                    is AppErrors.Unauthorized -> Icons.Default.Lock
                },
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color.Red,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error.message,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}
