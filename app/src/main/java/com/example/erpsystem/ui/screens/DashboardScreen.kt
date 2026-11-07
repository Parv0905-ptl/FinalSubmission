package com.example.erpsystem.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.theme.AccentCyan
import com.example.erpsystem.ui.theme.AccentTeal
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.BorderColor
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.ErrorRed
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.PrimaryDarkBlue
import com.example.erpsystem.ui.theme.PrimaryLightBlue
import com.example.erpsystem.ui.theme.SecondaryBlue
import com.example.erpsystem.ui.theme.SuccessGreen
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary
import com.example.erpsystem.ui.theme.TextSecondary
import com.example.erpsystem.ui.theme.WarningAmber

/**
 * Screen 10: Executive Dashboard with Full Module Navigation Connections
 */
@Composable
fun DashboardScreen(
    onNavigateToNotifications: () -> Unit = {},
    onNavigateToSearch: () -> Unit = {},
    onNavigateToInventory: () -> Unit = {},
    onNavigateToProducts: () -> Unit = {},
    onNavigateToStockMovement: () -> Unit = {},
    onNavigateToCustomers: () -> Unit = {},
    onNavigateToSuppliers: () -> Unit = {},
    onNavigateToCategories: () -> Unit = {},
    onNavigateToWarehouses: () -> Unit = {},
    onNavigateToSales: () -> Unit = {},
    onNavigateToPurchases: () -> Unit = {},
    onNavigateToQuotations: () -> Unit = {},
    onNavigateToInvoices: () -> Unit = {},
    onNavigateToPayments: () -> Unit = {}
) {
    var selectedBottomNav by remember { mutableIntStateOf(0) }
    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 10.dp
            ) {
                val navItems = listOf(
                    Triple("Home", Icons.Default.Home, 0),
                    Triple("Inventory", Icons.Default.Inventory, 1),
                    Triple("Products", Icons.Default.Inventory2, 2),
                    Triple("Customers", Icons.Default.People, 3),
                    Triple("Suppliers", Icons.Default.Store, 4)
                )

                navItems.forEach { (label, icon, index) ->
                    NavigationBarItem(
                        selected = selectedBottomNav == index,
                        onClick = {
                            selectedBottomNav = index
                            when (index) {
                                1 -> onNavigateToInventory()
                                2 -> onNavigateToProducts()
                                3 -> onNavigateToCustomers()
                                4 -> onNavigateToSuppliers()
                            }
                        },
                        icon = { Icon(imageVector = icon, contentDescription = label) },
                        label = { Text(text = label, fontWeight = if (selectedBottomNav == index) FontWeight.Bold else FontWeight.Medium) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = TextMuted,
                            unselectedTextColor = TextMuted,
                            indicatorColor = PrimaryLightBlue
                        )
                    )
                }
            }
        },
        containerColor = BackgroundLight
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // Header Section
            Surface(
                color = PrimaryDarkBlue,
                shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Good Morning, Alex 👋",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "HQ Tech Ltd • Admin Workspace",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = Color.White.copy(alpha = 0.7f),
                                    fontSize = 13.sp
                                )
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Search Action Button
                            IconButton(onClick = onNavigateToSearch) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = Color.White
                                )
                            }

                            // Notification Bell Button with Badge
                            IconButton(onClick = onNavigateToNotifications) {
                                BadgedBox(
                                    badge = {
                                        Badge(containerColor = ErrorRed) {
                                            Text("4", color = Color.White, fontWeight = FontWeight.Bold)
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = "Notifications",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Hero Metric Card
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(12.dp, RoundedCornerShape(20.dp), spotColor = AccentCyan),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.Transparent
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(PrimaryBlue, AccentCyan)
                                    )
                                )
                                .padding(20.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Total Monthly Revenue",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            color = Color.White.copy(alpha = 0.85f)
                                        )
                                    )
                                    Surface(
                                        color = Color.White.copy(alpha = 0.25f),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowUpward,
                                                contentDescription = null,
                                                tint = Color.White,
                                                modifier = Modifier.size(14.dp)
                                            )
                                            Spacer(modifier = Modifier.width(2.dp))
                                            Text(
                                                text = "+12.4%",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = Color.White,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = "$ 2,45,800.00",
                                    style = MaterialTheme.typography.headlineLarge.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 32.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Quick Access ERP Modules Section
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Text(
                    text = "Quick Access Modules 🚀",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Scrollable Quick Access Modules Chips
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickModuleChip(title = "Sales", icon = Icons.Default.ShoppingCart, color = PrimaryBlue, onClick = onNavigateToSales)
                    QuickModuleChip(title = "Purchases", icon = Icons.Default.Inventory2, color = AccentCyan, onClick = onNavigateToPurchases)
                    QuickModuleChip(title = "Invoices", icon = Icons.AutoMirrored.Filled.TrendingUp, color = SuccessGreen, onClick = onNavigateToInvoices)
                    QuickModuleChip(title = "Quotations", icon = Icons.Default.Category, color = WarningAmber, onClick = onNavigateToQuotations)
                    QuickModuleChip(title = "Payments", icon = Icons.Default.AttachMoney, color = SecondaryBlue, onClick = onNavigateToPayments)
                    QuickModuleChip(title = "Products", icon = Icons.Default.Inventory2, color = PrimaryBlue, onClick = onNavigateToProducts)
                    QuickModuleChip(title = "Stock Movement", icon = Icons.Default.SwapHoriz, color = WarningAmber, onClick = onNavigateToStockMovement)
                    QuickModuleChip(title = "Customers", icon = Icons.Default.People, color = AccentTeal, onClick = onNavigateToCustomers)
                    QuickModuleChip(title = "Suppliers", icon = Icons.Default.Store, color = SecondaryBlue, onClick = onNavigateToSuppliers)
                    QuickModuleChip(title = "Warehouses", icon = Icons.Default.Warehouse, color = SuccessGreen, onClick = onNavigateToWarehouses)
                    QuickModuleChip(title = "Categories", icon = Icons.Default.Category, color = PrimaryDarkBlue, onClick = onNavigateToCategories)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Grid KPI Metric Cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    KpiMetricCard(
                        modifier = Modifier.weight(1f),
                        title = "Products List",
                        value = "245 Items",
                        change = "+12 New",
                        icon = Icons.Default.Inventory2,
                        iconBg = PrimaryLightBlue,
                        iconColor = PrimaryBlue,
                        onClick = onNavigateToProducts
                    )

                    KpiMetricCard(
                        modifier = Modifier.weight(1f),
                        title = "Customers CRM",
                        value = "128 Clients",
                        change = "3 Pending",
                        icon = Icons.Default.People,
                        iconBg = AccentTeal.copy(alpha = 0.15f),
                        iconColor = AccentTeal,
                        onClick = onNavigateToCustomers
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    KpiMetricCard(
                        modifier = Modifier.weight(1f),
                        title = "Suppliers",
                        value = "45 Vendors",
                        change = "Active",
                        icon = Icons.Default.Store,
                        iconBg = SecondaryBlue.copy(alpha = 0.15f),
                        iconColor = SecondaryBlue,
                        onClick = onNavigateToSuppliers
                    )

                    KpiMetricCard(
                        modifier = Modifier.weight(1f),
                        title = "Warehouses",
                        value = "3 Locations",
                        change = "8,520 Pcs",
                        icon = Icons.Default.Warehouse,
                        iconBg = SuccessGreen.copy(alpha = 0.15f),
                        iconColor = SuccessGreen,
                        onClick = onNavigateToWarehouses
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Sales Trend Chart Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(6.dp, RoundedCornerShape(20.dp), spotColor = PrimaryBlue.copy(alpha = 0.1f)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Sales Overview",
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )
                                Text(
                                    text = "Weekly performance summary",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextSecondary,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.TrendingUp,
                                contentDescription = "Trend",
                                tint = SuccessGreen
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Chart Line Graph
                        DashboardLineChart(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Stock Movement Quick Logs Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Stock Movements 📊",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )
                    Text(
                        text = "View Log",
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable { onNavigateToStockMovement() }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                LowStockItemRow(name = "Logitech MX Keyboard", category = "Sales Order #1042 (-5 Pcs)", remaining = 5)
                Spacer(modifier = Modifier.height(8.dp))
                LowStockItemRow(name = "Wireless Optical Mouse", category = "Purchase Order #PO-0024 (+50 Pcs)", remaining = 120)

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun QuickModuleChip(
    title: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .shadow(4.dp, RoundedCornerShape(16.dp), spotColor = color),
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = color, modifier = Modifier.size(18.dp))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    fontSize = 13.sp
                )
            )
        }
    }
}

@Composable
private fun KpiMetricCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    change: String,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = icon, contentDescription = title, tint = iconColor, modifier = Modifier.size(22.dp))
                }

                Text(
                    text = change,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
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
private fun LowStockItemRow(
    name: String,
    category: String,
    remaining: Int
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        color = Color.White,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PrimaryLightBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Inventory2, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(20.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = name, style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, fontSize = 14.sp))
                    Text(text = category, style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, fontSize = 12.sp))
                }
            }

            Surface(
                color = PrimaryLightBlue,
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "$remaining units",
                    style = MaterialTheme.typography.labelMedium.copy(color = PrimaryBlue, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun DashboardLineChart(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height

        val points = listOf(
            Offset(0f, height * 0.7f),
            Offset(width * 0.2f, height * 0.5f),
            Offset(width * 0.4f, height * 0.6f),
            Offset(width * 0.6f, height * 0.25f),
            Offset(width * 0.8f, height * 0.35f),
            Offset(width, height * 0.15f)
        )

        val strokePath = Path().apply {
            moveTo(points[0].x, points[0].y)
            for (i in 1 until points.size) {
                val p0 = points[i - 1]
                val p1 = points[i]
                cubicTo(
                    (p0.x + p1.x) / 2, p0.y,
                    (p0.x + p1.x) / 2, p1.y,
                    p1.x, p1.y
                )
            }
        }

        drawPath(
            path = strokePath,
            color = PrimaryBlue,
            style = Stroke(width = 4.dp.toPx())
        )

        points.forEach { point ->
            drawCircle(color = PrimaryBlue, radius = 5.dp.toPx(), center = point)
            drawCircle(color = Color.White, radius = 3.dp.toPx(), center = point)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    ERPSystemTheme {
        DashboardScreen()
    }
}
