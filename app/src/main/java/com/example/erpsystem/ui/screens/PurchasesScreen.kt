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
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Store
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.BorderColor
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary
import com.example.erpsystem.ui.theme.WarningAmber

/**
 * Screen 35: Purchases Management Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchasesScreen(
    onNavigateBack: () -> Unit = {},
    onNavigateToPurchaseOrders: () -> Unit = {},
    onNavigateToGoodsReceived: () -> Unit = {},
    onNavigateToSuppliers: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Purchases & Vendor Orders",
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
                    IconButton(onClick = onNavigateToPurchaseOrders) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "New PO", tint = Color.White)
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
                .verticalScroll(scrollState)
                .padding(20.dp)
        ) {
            // KPI Summary Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                PurchaseKpiCard(
                    modifier = Modifier.weight(1f),
                    title = "Total Purchases",
                    value = "$ 45,800",
                    sub = "This Month",
                    icon = Icons.Default.ShoppingBag,
                    iconBg = PrimaryLightBlue,
                    iconColor = PrimaryBlue,
                    onClick = onNavigateToPurchaseOrders
                )

                PurchaseKpiCard(
                    modifier = Modifier.weight(1f),
                    title = "Goods Received (GRN)",
                    value = "12 Receipts",
                    sub = "2 Pending Inspection",
                    icon = Icons.Default.LocalShipping,
                    iconBg = SuccessGreen.copy(alpha = 0.15f),
                    iconColor = SuccessGreen,
                    onClick = onNavigateToGoodsReceived
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Purchase Navigation Modules
            Text(
                text = "Purchase Modules 📦",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PurchaseModuleRow(
                    title = "Purchase Orders",
                    subtitle = "Draft, send & track supplier purchase orders",
                    icon = Icons.Default.ShoppingBag,
                    onClick = onNavigateToPurchaseOrders
                )

                PurchaseModuleRow(
                    title = "Goods Received Notes (GRN)",
                    subtitle = "Verify warehouse stock receipts & shipments",
                    icon = Icons.Default.LocalShipping,
                    onClick = onNavigateToGoodsReceived
                )

                PurchaseModuleRow(
                    title = "Suppliers Directory",
                    subtitle = "Manage vendors, contacts & payment terms",
                    icon = Icons.Default.Store,
                    onClick = onNavigateToSuppliers
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun PurchaseKpiCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    sub: String,
    icon: ImageVector,
    iconBg: Color,
    iconColor: Color,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .shadow(4.dp, RoundedCornerShape(18.dp), spotColor = BorderColor)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = iconColor, modifier = Modifier.size(22.dp))
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = TextPrimary
                )
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = TextSecondary,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
private fun PurchaseModuleRow(
    title: String,
    subtitle: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryLightBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = PrimaryBlue, modifier = Modifier.size(24.dp))
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp))
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
            }

            Surface(
                color = PrimaryLightBlue,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Open",
                    style = MaterialTheme.typography.labelSmall.copy(color = PrimaryBlue, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PurchasesScreenPreview() {
    ERPSystemTheme {
        PurchasesScreen()
    }
}
