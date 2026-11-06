package com.example.erpsystem.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.AccentCyan
import com.example.erpsystem.ui.theme.AccentTeal
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.CardGradientEnd
import com.example.erpsystem.ui.theme.CardGradientStart
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryDarkBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary

data class OnboardingPageData(
    val step: Int,
    val title: String,
    val subtitle: String,
    val mainIcon: ImageVector,
    val badgeIcon1: ImageVector,
    val badgeIcon2: ImageVector,
    val badgeText1: String,
    val badgeText2: String
)

val onboardingPages = listOf(
    OnboardingPageData(
        step = 1,
        title = "Manage Your Business",
        subtitle = "Manage products, sales and customers from one unified place with zero hassle.",
        mainIcon = Icons.Default.BusinessCenter,
        badgeIcon1 = Icons.Default.PointOfSale,
        badgeIcon2 = Icons.Default.People,
        badgeText1 = "Sales & Orders",
        badgeText2 = "Customer CRM"
    ),
    OnboardingPageData(
        step = 2,
        title = "Track Everything",
        subtitle = "Monitor inventory, stock levels, warehouse, and business activities in real-time.",
        mainIcon = Icons.Default.Inventory2,
        badgeIcon1 = Icons.Default.Inventory2,
        badgeIcon2 = Icons.Default.Assessment,
        badgeText1 = "Stock Movement",
        badgeText2 = "Warehouse Logs"
    ),
    OnboardingPageData(
        step = 3,
        title = "Grow Your Business",
        subtitle = "Gain powerful data-driven insights through real-time reports, analytics, and forecasts.",
        mainIcon = Icons.Default.ShowChart,
        badgeIcon1 = Icons.Default.ShowChart,
        badgeIcon2 = Icons.Default.Assessment,
        badgeText1 = "Revenue Insights",
        badgeText2 = "Smart Analytics"
    )
)

/**
 * Screens 2, 3 & 4: Onboarding Flow (1/3, 2/3, 3/3)
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnboardingScreen(
    initialPage: Int = 0,
    onFinishOnboarding: () -> Unit = {}
) {
    var currentPage by remember { mutableIntStateOf(initialPage.coerceIn(0, 2)) }

    val pageData = onboardingPages[currentPage]

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundLight
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar: Step Indicator & Skip Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Step Badge (e.g., Onboarding 1/3)
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(PrimaryLightBlue)
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "Step ${currentPage + 1} of 3",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                if (currentPage < 2) {
                    TextButton(onClick = onFinishOnboarding) {
                        Text(
                            text = "Skip",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = TextSecondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Animated Screen Content
            AnimatedContent(
                targetState = currentPage,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally { width -> width } + fadeIn() with
                                slideOutHorizontally { width -> -width } + fadeOut()
                    } else {
                        slideInHorizontally { width -> -width } + fadeIn() with
                                slideOutHorizontally { width -> width } + fadeOut()
                    }
                },
                modifier = Modifier.weight(1f),
                label = "onboardingSlide"
            ) { pageIndex ->
                val page = onboardingPages[pageIndex]

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Modern Illustration Card Container
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .shadow(16.dp, RoundedCornerShape(28.dp), spotColor = PrimaryBlue.copy(alpha = 0.2f))
                            .clip(RoundedCornerShape(28.dp))
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(PrimaryDarkBlue, PrimaryBlue)
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        // Background decorative circles
                        Box(
                            modifier = Modifier
                                .size(220.dp)
                                .align(Alignment.TopEnd)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(AccentCyan.copy(alpha = 0.3f), Color.Transparent)
                                    )
                                )
                        )

                        // Center Main Hero Graphic
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = page.mainIcon,
                                    contentDescription = page.title,
                                    tint = Color.White,
                                    modifier = Modifier.size(48.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            // Badges Row inside card
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                OnboardingFeatureChip(icon = page.badgeIcon1, text = page.badgeText1)
                                OnboardingFeatureChip(icon = page.badgeIcon2, text = page.badgeText2)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    // Title
                    Text(
                        text = page.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 26.sp,
                            color = TextPrimary
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Subtitle
                    Text(
                        text = page.subtitle,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = TextSecondary,
                            fontSize = 15.sp,
                            lineHeight = 22.sp
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Carousel Dots Indicator
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                repeat(3) { index ->
                    val isSelected = index == currentPage
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .height(8.dp)
                            .width(if (isSelected) 28.dp else 8.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryBlue else TextMuted.copy(alpha = 0.4f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Bottom Action Button ("Next" / "Get Started")
            Button(
                onClick = {
                    if (currentPage < 2) {
                        currentPage++
                    } else {
                        onFinishOnboarding()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp), spotColor = PrimaryBlue),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = if (currentPage == 2) "Get Started" else "Next",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Next",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun OnboardingFeatureChip(
    icon: ImageVector,
    text: String
) {
    Surface(
        color = Color.White.copy(alpha = 0.2f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    ERPSystemTheme {
        OnboardingScreen()
    }
}
