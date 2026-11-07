package com.example.erpsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.erpsystem.ui.navigation.ErpNavigationApp
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
import com.example.erpsystem.ui.theme.ERPSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ERPSystemTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ErpNavigationApp()
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "1. Splash Screen")
@Composable
fun PreviewSplashScreen() {
    ERPSystemTheme {
        SplashScreen()
    }
}

@Preview(showBackground = true, name = "2. Onboarding 1 of 3")
@Composable
fun PreviewOnboarding1() {
    ERPSystemTheme {
        OnboardingScreen(initialPage = 0)
    }
}

@Preview(showBackground = true, name = "3. Onboarding 2 of 3")
@Composable
fun PreviewOnboarding2() {
    ERPSystemTheme {
        OnboardingScreen(initialPage = 1)
    }
}

@Preview(showBackground = true, name = "4. Onboarding 3 of 3")
@Composable
fun PreviewOnboarding3() {
    ERPSystemTheme {
        OnboardingScreen(initialPage = 2)
    }
}

@Preview(showBackground = true, name = "5. Login Screen")
@Composable
fun PreviewLoginScreen() {
    ERPSystemTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true, name = "6. Register Screen")
@Composable
fun PreviewRegisterScreen() {
    ERPSystemTheme {
        RegisterScreen()
    }
}

@Preview(showBackground = true, name = "7. Forgot Password Screen")
@Composable
fun PreviewForgotPasswordScreen() {
    ERPSystemTheme {
        ForgotPasswordScreen()
    }
}

@Preview(showBackground = true, name = "8. OTP Verification Screen")
@Composable
fun PreviewOtpVerificationScreen() {
    ERPSystemTheme {
        OtpVerificationScreen()
    }
}

@Preview(showBackground = true, name = "9. Create Company")
@Composable
fun PreviewCreateCompanyScreen() {
    ERPSystemTheme {
        CreateCompanyScreen()
    }
}

@Preview(showBackground = true, name = "10. Dashboard")
@Composable
fun PreviewDashboardScreen() {
    ERPSystemTheme {
        DashboardScreen()
    }
}

@Preview(showBackground = true, name = "11. Notifications")
@Composable
fun PreviewNotificationsScreen() {
    ERPSystemTheme {
        NotificationsScreen()
    }
}

@Preview(showBackground = true, name = "12. Global Search")
@Composable
fun PreviewGlobalSearchScreen() {
    ERPSystemTheme {
        GlobalSearchScreen()
    }
}

@Preview(showBackground = true, name = "13. Inventory Dashboard")
@Composable
fun PreviewInventoryDashboardScreen() {
    ERPSystemTheme {
        InventoryDashboardScreen()
    }
}

@Preview(showBackground = true, name = "14. Product List")
@Composable
fun PreviewProductListScreen() {
    ERPSystemTheme {
        ProductListScreen()
    }
}

@Preview(showBackground = true, name = "15. Add Product")
@Composable
fun PreviewAddProductScreen() {
    ERPSystemTheme {
        AddProductScreen()
    }
}

@Preview(showBackground = true, name = "16. Product Details")
@Composable
fun PreviewProductDetailsScreen() {
    ERPSystemTheme {
        ProductDetailsScreen()
    }
}

@Preview(showBackground = true, name = "17. Category")
@Composable
fun PreviewCategoryScreen() {
    ERPSystemTheme {
        CategoryScreen()
    }
}

@Preview(showBackground = true, name = "18. Warehouse")
@Composable
fun PreviewWarehouseScreen() {
    ERPSystemTheme {
        WarehouseScreen()
    }
}

@Preview(showBackground = true, name = "19. Warehouse Details")
@Composable
fun PreviewWarehouseDetailsScreen() {
    ERPSystemTheme {
        WarehouseDetailsScreen()
    }
}

@Preview(showBackground = true, name = "20. Stock Movement")
@Composable
fun PreviewStockMovementScreen() {
    ERPSystemTheme {
        StockMovementScreen()
    }
}

@Preview(showBackground = true, name = "21. Customer List")
@Composable
fun PreviewCustomerListScreen() {
    ERPSystemTheme {
        CustomerListScreen()
    }
}

@Preview(showBackground = true, name = "22. Add Customer")
@Composable
fun PreviewAddCustomerScreen() {
    ERPSystemTheme {
        AddCustomerScreen()
    }
}

@Preview(showBackground = true, name = "23. Customer Details")
@Composable
fun PreviewCustomerDetailsScreen() {
    ERPSystemTheme {
        CustomerDetailsScreen()
    }
}

@Preview(showBackground = true, name = "24. Supplier List")
@Composable
fun PreviewSupplierListScreen() {
    ERPSystemTheme {
        SupplierListScreen()
    }
}

@Preview(showBackground = true, name = "25. Add Supplier")
@Composable
fun PreviewAddSupplierScreen() {
    ERPSystemTheme {
        AddSupplierScreen()
    }
}

@Preview(showBackground = true, name = "26. Supplier Details")
@Composable
fun PreviewSupplierDetailsScreen() {
    ERPSystemTheme {
        SupplierDetailsScreen()
    }
}

@Preview(showBackground = true, name = "27. Sales Dashboard")
@Composable
fun PreviewSalesDashboardScreen() {
    ERPSystemTheme {
        SalesDashboardScreen()
    }
}

@Preview(showBackground = true, name = "28. Quotations")
@Composable
fun PreviewQuotationScreen() {
    ERPSystemTheme {
        QuotationScreen()
    }
}

@Preview(showBackground = true, name = "31. Sales Orders")
@Composable
fun PreviewSalesOrderListScreen() {
    ERPSystemTheme {
        SalesOrderListScreen()
    }
}

@Preview(showBackground = true, name = "32. Invoices")
@Composable
fun PreviewInvoiceListScreen() {
    ERPSystemTheme {
        InvoiceListScreen()
    }
}

@Preview(showBackground = true, name = "33. Invoice Details")
@Composable
fun PreviewInvoiceDetailsScreen() {
    ERPSystemTheme {
        InvoiceDetailsScreen()
    }
}

@Preview(showBackground = true, name = "34. Payments & Cashflow")
@Composable
fun PreviewPaymentsScreen() {
    ERPSystemTheme {
        PaymentsScreen()
    }
}

@Preview(showBackground = true, name = "35. Purchases Dashboard")
@Composable
fun PreviewPurchasesScreen() {
    ERPSystemTheme {
        PurchasesScreen()
    }
}

@Preview(showBackground = true, name = "36. Purchase Orders")
@Composable
fun PreviewPurchaseOrderListScreen() {
    ERPSystemTheme {
        PurchaseOrderListScreen()
    }
}

@Preview(showBackground = true, name = "37. Goods Received (GRN)")
@Composable
fun PreviewGoodsReceivedScreen() {
    ERPSystemTheme {
        GoodsReceivedScreen()
    }
}
