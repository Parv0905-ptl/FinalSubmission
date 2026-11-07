package com.example.erpsystem.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.erpsystem.ui.components.MessageBoxDialog
import com.example.erpsystem.ui.components.MessageBoxType
import com.example.erpsystem.ui.theme.BackgroundLight
import com.example.erpsystem.ui.theme.BorderColor
import com.example.erpsystem.ui.theme.ERPSystemTheme
import com.example.erpsystem.ui.theme.PrimaryBlue
import com.example.erpsystem.ui.theme.TextMuted
import com.example.erpsystem.ui.theme.TextPrimary

/**
 * Screen 22: Add Customer Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCustomerScreen(
    onNavigateBack: () -> Unit = {},
    onCustomerSaved: () -> Unit = {}
) {
    var contactName by remember { mutableStateOf("Alex Mercer") }
    var companyName by remember { mutableStateOf("ARC Pvt Ltd") }
    var phone by remember { mutableStateOf("+91 98765-11111") }
    var email by remember { mutableStateOf("contact@arc.com") }
    var address by remember { mutableStateOf("102 Tech Park, Mumbai") }
    var gstNumber by remember { mutableStateOf("27AAACH7409R1ZX") }
    var creditLimit by remember { mutableStateOf("5000.00") }

    var showDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add Customer",
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
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(8.dp, RoundedCornerShape(20.dp), spotColor = PrimaryBlue.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(20.dp),
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Contact Person", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = contactName, onValueChange = { contactName = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = PrimaryBlue) },
                        placeholder = { Text("Alex Mercer", color = TextMuted) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Company Name", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = companyName, onValueChange = { companyName = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Business, contentDescription = null, tint = PrimaryBlue) },
                        placeholder = { Text("ARC Pvt Ltd", color = TextMuted) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Phone Number", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = phone, onValueChange = { phone = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = PrimaryBlue) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Email Address", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = email, onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = PrimaryBlue) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Address", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = address, onValueChange = { address = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = PrimaryBlue) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("GST / Tax ID", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = gstNumber, onValueChange = { gstNumber = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.Receipt, contentDescription = null, tint = PrimaryBlue) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Credit Limit ($)", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = creditLimit, onValueChange = { creditLimit = it },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null, tint = PrimaryBlue) },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryBlue, unfocusedBorderColor = BorderColor,
                            focusedContainerColor = BackgroundLight, unfocusedContainerColor = BackgroundLight
                        ), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp), spotColor = PrimaryBlue),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue)
            ) {
                Text(
                    text = "Save Customer",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        MessageBoxDialog(
            show = showDialog,
            title = "Customer Saved! 👤",
            message = "\"$companyName\" ($contactName) has been added to customer directory.",
            type = MessageBoxType.SUCCESS,
            confirmButtonText = "View Customers",
            onConfirm = {
                showDialog = false
                onCustomerSaved()
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddCustomerScreenPreview() {
    ERPSystemTheme {
        AddCustomerScreen()
    }
}
