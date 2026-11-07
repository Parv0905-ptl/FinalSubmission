package com.example.erpsystem.ui.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.erpsystem.ui.screens.AddCustomerScreen
import com.example.erpsystem.ui.screens.AddProductScreen
import com.example.erpsystem.ui.screens.AddSupplierScreen
import com.example.erpsystem.ui.screens.CategoryScreen
import com.example.erpsystem.ui.screens.CreateCompanyScreen
import com.example.erpsystem.ui.screens.CustomerDetailsScreen
import com.example.erpsystem.ui.screens.CustomerListScreen
import com.example.erpsystem.ui.screens.DashboardScreen
import com.example.erpsystem.ui.screens.ForgotPasswordScreen
import com.example.erpsystem.ui.screens.GlobalSearchScreen
import com.example.erpsystem.ui.screens.GoodsReceivedScreen
import com.example.erpsystem.ui.screens.InventoryDashboardScreen
import com.example.erpsystem.ui.screens.InvoiceDetailsScreen
import com.example.erpsystem.ui.screens.InvoiceListScreen
import com.example.erpsystem.ui.screens.LoginScreen
import com.example.erpsystem.ui.screens.NotificationsScreen
import com.example.erpsystem.ui.screens.OnboardingScreen
import com.example.erpsystem.ui.screens.OtpVerificationScreen
import com.example.erpsystem.ui.screens.PaymentsScreen
import com.example.erpsystem.ui.screens.ProductDetailsScreen
import com.example.erpsystem.ui.screens.ProductListScreen
import com.example.erpsystem.ui.screens.PurchaseOrderListScreen
import com.example.erpsystem.ui.screens.PurchasesScreen
import com.example.erpsystem.ui.screens.QuotationScreen
import com.example.erpsystem.ui.screens.RegisterScreen
import com.example.erpsystem.ui.screens.SalesDashboardScreen
import com.example.erpsystem.ui.screens.SalesOrderListScreen
import com.example.erpsystem.ui.screens.SplashScreen
import com.example.erpsystem.ui.screens.StockMovementScreen
import com.example.erpsystem.ui.screens.SupplierDetailsScreen
import com.example.erpsystem.ui.screens.SupplierListScreen
import com.example.erpsystem.ui.screens.WarehouseDetailsScreen
import com.example.erpsystem.ui.screens.WarehouseScreen

enum class ErpScreen {
    SPLASH,
    ONBOARDING,
    LOGIN,
    REGISTER,
    FORGOT_PASSWORD,
    OTP_VERIFICATION,
    CREATE_COMPANY,
    DASHBOARD,
    NOTIFICATIONS,
    GLOBAL_SEARCH,
    INVENTORY_DASHBOARD,
    PRODUCT_LIST,
    ADD_PRODUCT,
    PRODUCT_DETAILS,
    CATEGORY,
    WAREHOUSE,
    WAREHOUSE_DETAILS,
    STOCK_MOVEMENT,
    CUSTOMER_LIST,
    ADD_CUSTOMER,
    CUSTOMER_DETAILS,
    SUPPLIER_LIST,
    ADD_SUPPLIER,
    SUPPLIER_DETAILS,
    SALES_DASHBOARD,
    QUOTATION_LIST,
    SALES_ORDER_LIST,
    INVOICE_LIST,
    INVOICE_DETAILS,
    PAYMENTS,
    PURCHASES_DASHBOARD,
    PURCHASE_ORDER_LIST,
    GOODS_RECEIVED
}

/**
 * Production App Navigation Controller.
 * Flows seamlessly through all ERP System screens.
 */
@Composable
fun ErpNavigationApp() {
    var currentScreen by remember { mutableStateOf(ErpScreen.SPLASH) }
    var userEmailForOtp by remember { mutableStateOf("john.doe@example.com") }
    var selectedProductId by remember { mutableStateOf("1") }
    var selectedWarehouseId by remember { mutableStateOf("1") }
    var selectedCustomerId by remember { mutableStateOf("1") }
    var selectedSupplierId by remember { mutableStateOf("1") }
    var selectedInvoiceId by remember { mutableStateOf("1") }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "screenTransition"
        ) { screen ->
            when (screen) {
                ErpScreen.SPLASH -> {
                    SplashScreen(
                        onNavigateNext = { currentScreen = ErpScreen.ONBOARDING }
                    )
                }
                ErpScreen.ONBOARDING -> {
                    OnboardingScreen(
                        initialPage = 0,
                        onFinishOnboarding = { currentScreen = ErpScreen.LOGIN }
                    )
                }
                ErpScreen.LOGIN -> {
                    LoginScreen(
                        onLoginSuccess = { currentScreen = ErpScreen.CREATE_COMPANY },
                        onNavigateToRegister = { currentScreen = ErpScreen.REGISTER },
                        onNavigateToForgotPassword = { currentScreen = ErpScreen.FORGOT_PASSWORD }
                    )
                }
                ErpScreen.REGISTER -> {
                    RegisterScreen(
                        onNavigateToLogin = { currentScreen = ErpScreen.LOGIN },
                        onRegisterSuccess = { currentScreen = ErpScreen.CREATE_COMPANY }
                    )
                }
                ErpScreen.FORGOT_PASSWORD -> {
                    ForgotPasswordScreen(
                        onNavigateBack = { currentScreen = ErpScreen.LOGIN },
                        onSendOtpSuccess = { email ->
                            userEmailForOtp = email
                            currentScreen = ErpScreen.OTP_VERIFICATION
                        }
                    )
                }
                ErpScreen.OTP_VERIFICATION -> {
                    OtpVerificationScreen(
                        email = userEmailForOtp,
                        onNavigateBack = { currentScreen = ErpScreen.FORGOT_PASSWORD },
                        onOtpVerifiedSuccess = { currentScreen = ErpScreen.CREATE_COMPANY }
                    )
                }
                ErpScreen.CREATE_COMPANY -> {
                    CreateCompanyScreen(
                        onNavigateBack = { currentScreen = ErpScreen.LOGIN },
                        onCompanyCreated = { currentScreen = ErpScreen.DASHBOARD }
                    )
                }
                ErpScreen.DASHBOARD -> {
                    DashboardScreen(
                        onNavigateToNotifications = { currentScreen = ErpScreen.NOTIFICATIONS },
                        onNavigateToSearch = { currentScreen = ErpScreen.GLOBAL_SEARCH },
                        onNavigateToInventory = { currentScreen = ErpScreen.INVENTORY_DASHBOARD },
                        onNavigateToProducts = { currentScreen = ErpScreen.PRODUCT_LIST },
                        onNavigateToStockMovement = { currentScreen = ErpScreen.STOCK_MOVEMENT },
                        onNavigateToCustomers = { currentScreen = ErpScreen.CUSTOMER_LIST },
                        onNavigateToSuppliers = { currentScreen = ErpScreen.SUPPLIER_LIST },
                        onNavigateToCategories = { currentScreen = ErpScreen.CATEGORY },
                        onNavigateToWarehouses = { currentScreen = ErpScreen.WAREHOUSE },
                        onNavigateToSales = { currentScreen = ErpScreen.SALES_DASHBOARD },
                        onNavigateToPurchases = { currentScreen = ErpScreen.PURCHASES_DASHBOARD },
                        onNavigateToQuotations = { currentScreen = ErpScreen.QUOTATION_LIST },
                        onNavigateToInvoices = { currentScreen = ErpScreen.INVOICE_LIST },
                        onNavigateToPayments = { currentScreen = ErpScreen.PAYMENTS }
                    )
                }
                ErpScreen.NOTIFICATIONS -> {
                    NotificationsScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD }
                    )
                }
                ErpScreen.GLOBAL_SEARCH -> {
                    GlobalSearchScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD }
                    )
                }
                ErpScreen.INVENTORY_DASHBOARD -> {
                    InventoryDashboardScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD },
                        onNavigateToAddProduct = { currentScreen = ErpScreen.ADD_PRODUCT },
                        onNavigateToProductsList = { currentScreen = ErpScreen.PRODUCT_LIST }
                    )
                }
                ErpScreen.PRODUCT_LIST -> {
                    ProductListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.INVENTORY_DASHBOARD },
                        onNavigateToAddProduct = { currentScreen = ErpScreen.ADD_PRODUCT },
                        onNavigateToProductDetails = { id ->
                            selectedProductId = id
                            currentScreen = ErpScreen.PRODUCT_DETAILS
                        }
                    )
                }
                ErpScreen.ADD_PRODUCT -> {
                    AddProductScreen(
                        onNavigateBack = { currentScreen = ErpScreen.PRODUCT_LIST },
                        onProductSaved = { currentScreen = ErpScreen.PRODUCT_LIST }
                    )
                }
                ErpScreen.PRODUCT_DETAILS -> {
                    ProductDetailsScreen(
                        productId = selectedProductId,
                        onNavigateBack = { currentScreen = ErpScreen.PRODUCT_LIST },
                        onNavigateToEditProduct = { currentScreen = ErpScreen.ADD_PRODUCT }
                    )
                }
                ErpScreen.CATEGORY -> {
                    CategoryScreen(
                        onNavigateBack = { currentScreen = ErpScreen.INVENTORY_DASHBOARD }
                    )
                }
                ErpScreen.WAREHOUSE -> {
                    WarehouseScreen(
                        onNavigateBack = { currentScreen = ErpScreen.INVENTORY_DASHBOARD },
                        onNavigateToWarehouseDetails = { id ->
                            selectedWarehouseId = id
                            currentScreen = ErpScreen.WAREHOUSE_DETAILS
                        }
                    )
                }
                ErpScreen.WAREHOUSE_DETAILS -> {
                    WarehouseDetailsScreen(
                        warehouseId = selectedWarehouseId,
                        onNavigateBack = { currentScreen = ErpScreen.WAREHOUSE }
                    )
                }
                ErpScreen.STOCK_MOVEMENT -> {
                    StockMovementScreen(
                        onNavigateBack = { currentScreen = ErpScreen.INVENTORY_DASHBOARD }
                    )
                }
                ErpScreen.CUSTOMER_LIST -> {
                    CustomerListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD },
                        onNavigateToAddCustomer = { currentScreen = ErpScreen.ADD_CUSTOMER },
                        onNavigateToCustomerDetails = { id ->
                            selectedCustomerId = id
                            currentScreen = ErpScreen.CUSTOMER_DETAILS
                        }
                    )
                }
                ErpScreen.ADD_CUSTOMER -> {
                    AddCustomerScreen(
                        onNavigateBack = { currentScreen = ErpScreen.CUSTOMER_LIST },
                        onCustomerSaved = { currentScreen = ErpScreen.CUSTOMER_LIST }
                    )
                }
                ErpScreen.CUSTOMER_DETAILS -> {
                    CustomerDetailsScreen(
                        customerId = selectedCustomerId,
                        onNavigateBack = { currentScreen = ErpScreen.CUSTOMER_LIST }
                    )
                }
                ErpScreen.SUPPLIER_LIST -> {
                    SupplierListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD },
                        onNavigateToAddSupplier = { currentScreen = ErpScreen.ADD_SUPPLIER }
                    )
                }
                ErpScreen.ADD_SUPPLIER -> {
                    AddSupplierScreen(
                        onNavigateBack = { currentScreen = ErpScreen.SUPPLIER_LIST },
                        onSupplierSaved = { currentScreen = ErpScreen.SUPPLIER_LIST }
                    )
                }
                ErpScreen.SUPPLIER_DETAILS -> {
                    SupplierDetailsScreen(
                        supplierId = selectedSupplierId,
                        onNavigateBack = { currentScreen = ErpScreen.SUPPLIER_LIST }
                    )
                }
                ErpScreen.SALES_DASHBOARD -> {
                    SalesDashboardScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD },
                        onNavigateToSalesOrders = { currentScreen = ErpScreen.SALES_ORDER_LIST },
                        onNavigateToInvoices = { currentScreen = ErpScreen.INVOICE_LIST },
                        onNavigateToQuotations = { currentScreen = ErpScreen.QUOTATION_LIST },
                        onNavigateToPayments = { currentScreen = ErpScreen.PAYMENTS }
                    )
                }
                ErpScreen.QUOTATION_LIST -> {
                    QuotationScreen(
                        onNavigateBack = { currentScreen = ErpScreen.SALES_DASHBOARD }
                    )
                }
                ErpScreen.SALES_ORDER_LIST -> {
                    SalesOrderListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.SALES_DASHBOARD }
                    )
                }
                ErpScreen.INVOICE_LIST -> {
                    InvoiceListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.SALES_DASHBOARD },
                        onNavigateToInvoiceDetails = { id ->
                            selectedInvoiceId = id
                            currentScreen = ErpScreen.INVOICE_DETAILS
                        }
                    )
                }
                ErpScreen.INVOICE_DETAILS -> {
                    InvoiceDetailsScreen(
                        invoiceId = selectedInvoiceId,
                        onNavigateBack = { currentScreen = ErpScreen.INVOICE_LIST }
                    )
                }
                ErpScreen.PAYMENTS -> {
                    PaymentsScreen(
                        onNavigateBack = { currentScreen = ErpScreen.SALES_DASHBOARD }
                    )
                }
                ErpScreen.PURCHASES_DASHBOARD -> {
                    PurchasesScreen(
                        onNavigateBack = { currentScreen = ErpScreen.DASHBOARD },
                        onNavigateToPurchaseOrders = { currentScreen = ErpScreen.PURCHASE_ORDER_LIST },
                        onNavigateToGoodsReceived = { currentScreen = ErpScreen.GOODS_RECEIVED },
                        onNavigateToSuppliers = { currentScreen = ErpScreen.SUPPLIER_LIST }
                    )
                }
                ErpScreen.PURCHASE_ORDER_LIST -> {
                    PurchaseOrderListScreen(
                        onNavigateBack = { currentScreen = ErpScreen.PURCHASES_DASHBOARD }
                    )
                }
                ErpScreen.GOODS_RECEIVED -> {
                    GoodsReceivedScreen(
                        onNavigateBack = { currentScreen = ErpScreen.PURCHASES_DASHBOARD }
                    )
                }
            }
        }
    }
}
