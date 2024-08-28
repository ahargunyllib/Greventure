package com.seven_sheesh.greventure.presentation.ui.widget

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.seven_sheesh.greventure.presentation.ui.design_system.GreventureScheme

@Composable
fun BulletProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Int = 3,
    totalNumberOfBars: Int = 12
) {
    Canvas(modifier = modifier) {
        val barArea = size.width / totalNumberOfBars
        val barLength = barArea - 2.dp.toPx()

        var nextBarStartPosition = 0F

        for (i in 0..totalNumberOfBars) {
            val barStartPosition = nextBarStartPosition + 8.dp.toPx()
            val barEndPosition = barStartPosition + barLength

            val start = Offset(x = barStartPosition, y = size.height / 2)
            val end = Offset(x = barEndPosition, y = size.height / 2)

            drawLine(
                cap = StrokeCap.Round,
                color = if (i+1 == progress) GreventureScheme.Primary.color else GreventureScheme.Primary.color.copy(
                    alpha = .5F
                ),
                start = start,
                end = end,
                strokeWidth = 36F
            )

            nextBarStartPosition = barEndPosition + 16.dp.toPx()
        }
    }
}