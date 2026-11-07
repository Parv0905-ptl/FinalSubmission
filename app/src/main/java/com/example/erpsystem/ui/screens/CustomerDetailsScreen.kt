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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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

/**
 * Screen 23: Customer Details Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailsScreen(
    customerId: String = "1",
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
                        text = "Customer Details",
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
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = PrimaryBlue)
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
            // Customer Header Card
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(PrimaryLightBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ARC",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryBlue,
                                    fontSize = 18.sp
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(text = "ARC Pvt Ltd", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, fontSize = 20.sp))
                            Text(text = "Contact: Alex Mercer • GST: 27AAACH7409R1ZX", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Total Sales", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                            Text("$ 24,500.00", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = SuccessGreen))
                        }

                        Column {
                            Text("Outstanding Balance", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                            Text("$ 1,200.00", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = WarningAmber))
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "+91 98765-11111", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 13.sp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "contact@arc.com", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 13.sp))
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "102 Tech Park, Mumbai", style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 13.sp))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(
                            onClick = {
                                dialogTitle = "Create Sales Order"
                                dialogMessage = "Starting new sales order for ARC Pvt Ltd."
                                showDialog = true
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Create Order", fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = {
                                dialogTitle = "Customer Contact"
                                dialogMessage = "Calling Alex Mercer (+91 98765-11111)..."
                                showDialog = true
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Call", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Recent Orders Header
            Text(
                text = "Recent Orders",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomerOrderCard(orderId = "Order #1042", date = "Today", amount = "$ 4,200.00", status = "Completed", isCompleted = true)
            Spacer(modifier = Modifier.height(10.dp))
            CustomerOrderCard(orderId = "Order #1020", date = "5 days ago", amount = "$ 1,200.00", status = "Payment Pending", isCompleted = false)

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
private fun CustomerOrderCard(
    orderId: String,
    date: String,
    amount: String,
    status: String,
    isCompleted: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = orderId, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 15.sp))
                Text(text = date, style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(text = amount, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = PrimaryBlue, fontSize = 15.sp))
                Surface(
                    color = if (isCompleted) SuccessGreen.copy(alpha = 0.15f) else WarningAmber.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = status,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = if (isCompleted) SuccessGreen else WarningAmber,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomerDetailsScreenPreview() {
    ERPSystemTheme {
        CustomerDetailsScreen()
    }
}
