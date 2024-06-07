package com.devmike.commonui.sharedui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.devmike.commonui.R

@Composable
fun CalorieItemScreen(amount: Double) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter =
                painterResource(
                    R.drawable.flame_danger,
                ),
            contentDescription = "Calories",
            modifier = Modifier.size(40.dp),
            tint = Color(0xFFFF4D00),
        )
        Text(
            text =
                buildAnnotatedString {
                    withStyle(
                        style =
                            SpanStyle(
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Bold,
                            ),
                    ) {
                        append(amount.toString())
                    }

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                        append(" Kcal")
                    }
                },
        )
    }
}
