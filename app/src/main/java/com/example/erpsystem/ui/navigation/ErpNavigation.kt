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
import com.example.erpsystem.ui.screens.AddProductScreen
import com.example.erpsystem.ui.screens.CategoryScreen
import com.example.erpsystem.ui.screens.CreateCompanyScreen
import com.example.erpsystem.ui.screens.DashboardScreen
import com.example.erpsystem.ui.screens.ForgotPasswordScreen
import com.example.erpsystem.ui.screens.GlobalSearchScreen
import com.example.erpsystem.ui.screens.InventoryDashboardScreen
import com.example.erpsystem.ui.screens.LoginScreen
import com.example.erpsystem.ui.screens.NotificationsScreen
import com.example.erpsystem.ui.screens.OnboardingScreen
import com.example.erpsystem.ui.screens.OtpVerificationScreen
import com.example.erpsystem.ui.screens.ProductDetailsScreen
import com.example.erpsystem.ui.screens.ProductListScreen
import com.example.erpsystem.ui.screens.RegisterScreen
import com.example.erpsystem.ui.screens.SplashScreen
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
    WAREHOUSE_DETAILS
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
                        onNavigateToInventory = { currentScreen = ErpScreen.INVENTORY_DASHBOARD }
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
            }
        }
    }
}
