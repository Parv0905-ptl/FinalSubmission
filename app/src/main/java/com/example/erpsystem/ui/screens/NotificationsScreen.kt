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
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val timestamp: String,
    val category: String, // "Business", "Tasks", "Payments"
    val isUnread: Boolean,
    val icon: ImageVector,
    val iconBg: Color,
    val iconColor: Color
)

val sampleNotifications = listOf(
    NotificationItem(
        id = "1",
        title = "Low Stock Alert ⚠️",
        message = "Logitech MX Keyboard is running low on stock (only 5 units remaining in Main Warehouse).",
        timestamp = "2m ago",
        category = "Business",
        isUnread = true,
        icon = Icons.Default.Warning,
        iconBg = ErrorRed.copy(alpha = 0.15f),
        iconColor = ErrorRed
    ),
    NotificationItem(
        id = "2",
        title = "Payment Due 💳",
        message = "Invoice #INV-2045 payment of ₹ 32,500 is due today from ABC Enterprise.",
        timestamp = "1h ago",
        category = "Payments",
        isUnread = true,
        icon = Icons.Default.Payment,
        iconBg = WarningAmber.copy(alpha = 0.15f),
        iconColor = WarningAmber
    ),
    NotificationItem(
        id = "3",
        title = "New Sales Order 🛒",
        message = "New sales order #1042 received from ARC Pvt Ltd totaling $ 4,200.00.",
        timestamp = "3h ago",
        category = "Business",
        isUnread = true,
        icon = Icons.Default.ShoppingCart,
        iconBg = PrimaryLightBlue,
        iconColor = PrimaryBlue
    ),
    NotificationItem(
        id = "4",
        title = "New Task Assigned 📋",
        message = "Assignee: Review quarterly inventory audit report and sign off.",
        timestamp = "5h ago",
        category = "Tasks",
        isUnread = false,
        icon = Icons.AutoMirrored.Filled.Assignment,
        iconBg = PrimaryLightBlue,
        iconColor = PrimaryBlue
    ),
    NotificationItem(
        id = "5",
        title = "System Backup Complete ✅",
        message = "Daily cloud database backup executed successfully at 02:00 AM.",
        timestamp = "1d ago",
        category = "Business",
        isUnread = false,
        icon = Icons.Default.CheckCircle,
        iconBg = SuccessGreen.copy(alpha = 0.15f),
        iconColor = SuccessGreen
    )
)

/**
 * Screen 11: Notifications Center
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    onNavigateBack: () -> Unit = {}
) {
    var selectedFilter by remember { mutableStateOf("All") }
    var notificationList by remember { mutableStateOf(sampleNotifications) }
    var selectedNotification by remember { mutableStateOf<NotificationItem?>(null) }

    val categories = listOf("All", "Business", "Tasks", "Payments")

    val filteredList = if (selectedFilter == "All") {
        notificationList
    } else {
        notificationList.filter { it.category == selectedFilter }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notifications",
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
                    TextButton(
                        onClick = {
                            notificationList = notificationList.map { it.copy(isUnread = false) }
                        }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.DoneAll, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Read All", style = MaterialTheme.typography.labelLarge.copy(color = PrimaryBlue, fontWeight = FontWeight.Bold))
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
        ) {
            // Horizontal Category Filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    val isSelected = selectedFilter == category
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedFilter = category },
                        label = { Text(text = category, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
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

            Spacer(modifier = Modifier.height(8.dp))

            // Notifications List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList, key = { it.id }) { item ->
                    NotificationCard(
                        item = item,
                        onClick = {
                            selectedNotification = item
                            notificationList = notificationList.map {
                                if (it.id == item.id) it.copy(isUnread = false) else it
                            }
                        }
                    )
                }
            }
        }

        // Selected Notification Dialog Details
        selectedNotification?.let { item ->
            MessageBoxDialog(
                show = true,
                title = item.title,
                message = "${item.message}\n\nTime: ${item.timestamp}",
                type = MessageBoxType.NOTIFICATION,
                confirmButtonText = "Close",
                onConfirm = { selectedNotification = null },
                onDismiss = { selectedNotification = null }
            )
        }
    }
}

@Composable
private fun NotificationCard(
    item: NotificationItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isUnread) PrimaryLightBlue.copy(alpha = 0.35f) else Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (item.isUnread) 4.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(item.iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.iconColor,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = if (item.isUnread) FontWeight.Bold else FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = TextPrimary
                        )
                    )

                    if (item.isUnread) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.message,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = TextSecondary,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.timestamp,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = TextMuted,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationsScreenPreview() {
    ERPSystemTheme {
        NotificationsScreen()
    }
}
