package com.example.erpsystem.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.ErrorRed
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary

data class PaymentLogItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val amount: String,
    val isIncome: Boolean
)

val samplePaymentLogs = listOf(
    PaymentLogItem("1", "Payment Received - ARC Pvt Ltd", "Bank Transfer • INV-2042", "Today, 11:30 AM", "+$ 4,200.00", true),
    PaymentLogItem("2", "Supplier Paid - Global Tech", "PO #PO-0024", "Yesterday", "-$ 12,300.00", false),
    PaymentLogItem("3", "Payment Received - Fresh Traders", "UPI • INV-2035", "2 days ago", "+$ 450.00", true)
)

/**
 * Screen 34: Payments & Cashflow Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsScreen(
    onNavigateBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Payments & Expenses",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = TextPrimary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BackgroundLight)
            )
        },
        containerColor = BackgroundLight
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            // Summary KPI Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total Received", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                        Text("$ 20,450.00", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = SuccessGreen, fontSize = 18.sp))
                    }
                }

                Surface(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Total Paid Out", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                        Text("$ 12,300.00", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = ErrorRed, fontSize = 18.sp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Transaction History",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(samplePaymentLogs, key = { it.id }) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(if (item.isIncome) SuccessGreen.copy(alpha = 0.15f) else ErrorRed.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (item.isIncome) Icons.Default.ArrowDownward else Icons.Default.ArrowUpward,
                                        contentDescription = null,
                                        tint = if (item.isIncome) SuccessGreen else ErrorRed,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column {
                                    Text(text = item.title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                                    Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                                    Text(text = item.date, style = MaterialTheme.typography.labelSmall.copy(color = TextMuted, fontSize = 11.sp))
                                }
                            }

                            Text(
                                text = item.amount,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (item.isIncome) SuccessGreen else ErrorRed,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentsScreenPreview() {
    ERPSystemTheme {
        PaymentsScreen()
    }
}
