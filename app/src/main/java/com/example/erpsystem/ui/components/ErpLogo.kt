package com.example.erpsystem.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.AccentTeal
import com.example.erpsystem.ui.theme.CardGradientEnd
import com.example.erpsystem.ui.theme.CardGradientStart
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.TextPrimary

@Composable
fun ErpLogoIcon(
    size: Dp = 64.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(12.dp, RoundedCornerShape(20.dp), spotColor = PrimaryBlue)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(CardGradientStart, CardGradientEnd)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.TrendingUp,
            contentDescription = "ERP Logo Icon",
            tint = Color.White,
            modifier = Modifier.size(size * 0.55f)
        )
    }
}

@Composable
fun ErpHeaderBrand(
    modifier: Modifier = Modifier,
    isDarkBg: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ErpLogoIcon(size = 48.dp)
        Spacer(modifier = Modifier.width(12.dp))
        Box {
            Text(
                text = "ERP Business",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = if (isDarkBg) Color.White else TextPrimary
                )
            )
        }
    }
}
