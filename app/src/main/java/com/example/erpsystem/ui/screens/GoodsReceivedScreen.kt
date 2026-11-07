package com.example.erpsystem.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocalShipping
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
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary
import com.example.erpsystem.ui.theme.WarningAmber

data class GrnItemData(
    val id: String,
    val grnNo: String,
    val poNo: String,
    val supplier: String,
    val units: String,
    val date: String,
    val status: String
)

val sampleGrnItems = listOf(
    GrnItemData("1", "GRN-0012", "PO #PO-0024", "Global Tech Distributors", "50 Units Received", "Yesterday", "Inspected"),
    GrnItemData("2", "GRN-0011", "PO #PO-0022", "AMC Suppliers", "10 Units Received", "3 days ago", "Pending Audit")
)

/**
 * Screen 37: Goods Received Notes (GRN) Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoodsReceivedScreen(
    onNavigateBack: () -> Unit = {}
) {
    var selectedGrnDialog by remember { mutableStateOf<GrnItemData?>(null) }
    var showCreateDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Goods Received (GRN)",
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
                            Icon(imageVector = Icons.Default.Add, contentDescription = "New GRN", tint = Color.White)
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
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleGrnItems, key = { it.id }) { grn ->
                    val (statusColor, statusBg) = if (grn.status == "Inspected") {
                        Pair(SuccessGreen, SuccessGreen.copy(alpha = 0.15f))
                    } else {
                        Pair(WarningAmber, WarningAmber.copy(alpha = 0.15f))
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedGrnDialog = grn },
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
                                        .background(PrimaryLightBlue),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.LocalShipping, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(24.dp))
                                }

                                Spacer(modifier = Modifier.width(14.dp))

                                Column {
                                    Text(text = "${grn.grnNo} (${grn.poNo})", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 15.sp))
                                    Text(text = "${grn.supplier} • ${grn.date}", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                                    Text(text = grn.units, style = MaterialTheme.typography.labelMedium.copy(color = PrimaryBlue, fontWeight = FontWeight.SemiBold))
                                }
                            }

                            Surface(
                                color = statusBg,
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = grn.status,
                                    style = MaterialTheme.typography.labelSmall.copy(color = statusColor, fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        if (showCreateDialog) {
            MessageBoxDialog(
                show = true,
                title = "New Goods Receipt Note (GRN) 🚚",
                message = "Record incoming shipment from supplier into warehouse.",
                type = MessageBoxType.INFO,
                confirmButtonText = "Record GRN",
                onConfirm = { showCreateDialog = false },
                onDismiss = { showCreateDialog = false }
            )
        }

        selectedGrnDialog?.let { grn ->
            MessageBoxDialog(
                show = true,
                title = "Goods Receipt ${grn.grnNo} 📦",
                message = "PO Reference: ${grn.poNo}\nSupplier: ${grn.supplier}\nDetails: ${grn.units}\nStatus: ${grn.status}",
                type = MessageBoxType.INFO,
                confirmButtonText = "Close",
                onConfirm = { selectedGrnDialog = null },
                onDismiss = { selectedGrnDialog = null }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoodsReceivedScreenPreview() {
    ERPSystemTheme {
        GoodsReceivedScreen()
    }
}
