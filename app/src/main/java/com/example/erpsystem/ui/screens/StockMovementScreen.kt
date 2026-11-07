package com.example.erpsystem.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.ErrorRed
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary

data class StockMovementItem(
    val id: String,
    val productName: String,
    val reference: String,
    val warehouse: String,
    val date: String,
    val changeQty: String,
    val isAddition: Boolean,
    val icon: ImageVector
)

val sampleMovements = listOf(
    StockMovementItem("1", "Logitech MX Keyboard", "Sales Order #1042", "Main Warehouse", "Today, 10:30 AM", "-5 Pcs", false, Icons.Default.ArrowUpward),
    StockMovementItem("2", "Wireless Optical Mouse", "Purchase Order #PO-0024", "Main Warehouse", "Yesterday, 04:15 PM", "+50 Pcs", true, Icons.Default.ArrowDownward),
    StockMovementItem("3", "Laptop Pro 15\"", "Sales Order #1041", "Branch Warehouse", "2 days ago", "-2 Pcs", false, Icons.Default.ArrowUpward),
    StockMovementItem("4", "HP LaserJet Printer", "Stock Transfer (Depot -> Main)", "Main Warehouse", "3 days ago", "+10 Pcs", true, Icons.Default.SwapHoriz)
)

/**
 * Screen 20: Stock Movement Log Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockMovementScreen(
    onNavigateBack: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("All") }
    val filters = listOf("All", "Stock In ⬇️", "Stock Out ⬆️", "Transfers")

    val filteredList = when (selectedFilter) {
        "Stock In ⬇️" -> sampleMovements.filter { it.isAddition }
        "Stock Out ⬆️" -> sampleMovements.filter { !it.isAddition && it.icon != Icons.Default.SwapHoriz }
        "Transfers" -> sampleMovements.filter { it.icon == Icons.Default.SwapHoriz }
        else -> sampleMovements
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Stock Movement",
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
            // Filter Chips
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = selectedFilter == filter
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedFilter = filter },
                        label = { Text(text = filter, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PrimaryBlue,
                            selectedLabelColor = Color.White,
                            containerColor = Color.White,
                            labelColor = TextSecondary
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Logs List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredList, key = { it.id }) { item ->
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
                                        .background(if (item.isAddition) SuccessGreen.copy(alpha = 0.15f) else ErrorRed.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = null,
                                        tint = if (item.isAddition) SuccessGreen else ErrorRed,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column {
                                    Text(text = item.productName, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 15.sp))
                                    Text(text = "${item.reference} • ${item.warehouse}", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                                    Text(text = item.date, style = MaterialTheme.typography.labelSmall.copy(color = TextMuted, fontSize = 11.sp))
                                }
                            }

                            Text(
                                text = item.changeQty,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (item.isAddition) SuccessGreen else ErrorRed,
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
fun StockMovementScreenPreview() {
    ERPSystemTheme {
        StockMovementScreen()
    }
}
