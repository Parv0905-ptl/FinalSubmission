package com.example.erpsystem.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.components.MessageBoxDialog
import com.example.erpsystem.ui.components.MessageBoxType
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.BorderColor
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.ErrorRed
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary
import com.example.erpsystem.ui.theme.WarningAmber

data class QuotationItemData(
    val id: String,
    val quoteNo: String,
    val customer: String,
    val amount: String,
    val date: String,
    val status: String,
    val isAccepted: Boolean
)

val sampleQuotations = listOf(
    QuotationItemData("1", "QT-1021", "ARC Pvt Ltd", "$ 1,450.00", "Expires in 3 days", "Sent", false),
    QuotationItemData("2", "QT-1022", "XYZ Enterprises", "$ 2,800.00", "Accepted", "Accepted", true),
    QuotationItemData("3", "QT-1019", "Fresh Traders", "$ 920.00", "Expired 2 days ago", "Expired", false)
)

/**
 * Screen 28: Quotation Management Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotationScreen(
    onNavigateBack: () -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }
    var selectedQuoteDialog by remember { mutableStateOf<QuotationItemData?>(null) }
    var showCreateDialog by remember { mutableStateOf(false) }

    val filters = listOf("All", "Sent", "Accepted", "Expired")

    val filteredList = sampleQuotations.filter { quote ->
        val matchesQuery = quote.quoteNo.contains(searchQuery, ignoreCase = true) || quote.customer.contains(searchQuery, ignoreCase = true)
        val matchesFilter = when (selectedFilter) {
            "Sent" -> quote.status == "Sent"
            "Accepted" -> quote.status == "Accepted"
            "Expired" -> quote.status == "Expired"
            else -> true
        }
        matchesQuery && matchesFilter
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Quotations & Estimates",
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
                actions = {
                    IconButton(onClick = { showCreateDialog = true }) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "New Quotation", tint = Color.White)
                        }
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
            // Search Input
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search quote #, customer...", color = TextMuted) },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = PrimaryBlue) },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                    focusedContainerColor = Color.White, unfocusedContainerColor = Color.White
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Filter Chips Row
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

            // Quotations List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredList, key = { it.id }) { quote ->
                    val (statusColor, statusBg) = when (quote.status) {
                        "Accepted" -> Pair(SuccessGreen, SuccessGreen.copy(alpha = 0.15f))
                        "Sent" -> Pair(PrimaryBlue, PrimaryLightBlue)
                        else -> Pair(ErrorRed, ErrorRed.copy(alpha = 0.15f))
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedQuoteDialog = quote },
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
                                        .size(46.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(statusBg),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.Description, contentDescription = null, tint = statusColor, modifier = Modifier.size(24.dp))
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column {
                                    Text(text = quote.quoteNo, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                                    Text(text = "${quote.customer} • ${quote.date}", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                                }
                            }

                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = quote.amount, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = PrimaryBlue, fontSize = 15.sp))
                                Surface(
                                    color = statusBg,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = quote.status,
                                        style = MaterialTheme.typography.labelSmall.copy(color = statusColor, fontWeight = FontWeight.Bold),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showCreateDialog) {
            MessageBoxDialog(
                show = true,
                title = "Create New Quotation 📝",
                message = "Generate a new price estimate for your client.",
                type = MessageBoxType.INFO,
                confirmButtonText = "Create",
                onConfirm = { showCreateDialog = false },
                onDismiss = { showCreateDialog = false }
            )
        }

        selectedQuoteDialog?.let { quote ->
            MessageBoxDialog(
                show = true,
                title = "Quotation ${quote.quoteNo} 📄",
                message = "Customer: ${quote.customer}\nAmount: ${quote.amount}\nStatus: ${quote.status}",
                type = MessageBoxType.INFO,
                confirmButtonText = if (quote.isAccepted) "Convert to Sales Order" else "Close",
                onConfirm = { selectedQuoteDialog = null },
                onDismiss = { selectedQuoteDialog = null }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuotationScreenPreview() {
    ERPSystemTheme {
        QuotationScreen()
    }
}
