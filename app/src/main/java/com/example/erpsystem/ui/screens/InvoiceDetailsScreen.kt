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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.shadow
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
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary
import com.example.erpsystem.ui.theme.WarningAmber

/**
 * Screen 33: Invoice Details Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvoiceDetailsScreen(
    invoiceId: String = "1",
    onNavigateBack: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Invoice #INV-2045",
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
                    IconButton(onClick = {
                        dialogTitle = "Share Invoice"
                        dialogMessage = "Sharing Invoice #INV-2045 PDF with client."
                        showDialog = true
                    }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share", tint = PrimaryBlue)
                    }
                    IconButton(onClick = {
                        dialogTitle = "Download PDF"
                        dialogMessage = "Invoice #INV-2045 PDF downloaded to device storage."
                        showDialog = true
                    }) {
                        Icon(imageVector = Icons.Default.Download, contentDescription = "Download", tint = PrimaryBlue)
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
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // Invoice Header Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(20.dp), spotColor = PrimaryBlue.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(20.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Billed To:", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                            Text("ABC Enterprise", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                            Text("GST: 27AAACH7409R1ZX", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                        }

                        Surface(
                            color = WarningAmber.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("UNPAID", style = MaterialTheme.typography.labelSmall.copy(color = WarningAmber, fontWeight = FontWeight.Bold), modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = BorderColor)
                    Spacer(modifier = Modifier.height(20.dp))

                    // Itemized List Header
                    Text("Item Breakdown", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                    Spacer(modifier = Modifier.height(12.dp))

                    InvoiceLineRow("Laptop Pro 15\"", "2 Pcs x $ 950.00", "$ 1,900.00")
                    Spacer(modifier = Modifier.height(8.dp))
                    InvoiceLineRow("Logitech MX Keyboard", "5 Pcs x $ 75.00", "$ 375.00")

                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalDivider(color = BorderColor)
                    Spacer(modifier = Modifier.height(16.dp))

                    // Totals
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Subtotal", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary))
                        Text("$ 2,275.00", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("GST Tax (18%)", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary))
                        Text("$ 409.50", style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = BorderColor)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Grand Total", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                        Text("$ 2,684.50", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = PrimaryBlue, fontSize = 22.sp))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            dialogTitle = "Record Payment"
                            dialogMessage = "Recording full payment of $ 2,684.50 from ABC Enterprise."
                            showDialog = true
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .shadow(6.dp, RoundedCornerShape(12.dp), spotColor = PrimaryBlue),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                    ) {
                        Icon(imageVector = Icons.Default.Payment, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Record Payment", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = Color.White, fontSize = 16.sp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        MessageBoxDialog(
            show = showDialog,
            title = dialogTitle,
            message = dialogMessage,
            type = MessageBoxType.INFO,
            confirmButtonText = "Close",
            onConfirm = { showDialog = false },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
private fun InvoiceLineRow(name: String, qty: String, total: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp))
            Text(text = qty, style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
        }
        Text(text = total, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp))
    }
}

@Preview(showBackground = true)
@Composable
fun InvoiceDetailsScreenPreview() {
    ERPSystemTheme {
        InvoiceDetailsScreen()
    }
}
