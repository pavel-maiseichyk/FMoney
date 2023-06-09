package com.pm.savings

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pm.savings.presentation.category_details.CategoryDetailScreen
import com.pm.savings.presentation.category_details.CategoryDetailsEvent
import com.pm.savings.presentation.category_details.CategoryDetailsViewModel
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.core.UiEvent
import com.pm.savings.presentation.core.navigation.BottomBar
import com.pm.savings.presentation.core.navigation.Routes
import com.pm.savings.presentation.home.HomeEvent
import com.pm.savings.presentation.home.HomeScreen
import com.pm.savings.presentation.home.HomeState
import com.pm.savings.presentation.home.HomeViewModel
import com.pm.savings.presentation.operation_details.OperationDetailsEvent
import com.pm.savings.presentation.operation_details.OperationDetailsScreen
import com.pm.savings.presentation.operation_details.OperationDetailsViewModel
import com.pm.savings.presentation.profile.ProfileEvent
import com.pm.savings.presentation.profile.ProfileScreen
import com.pm.savings.presentation.profile.ProfileViewModel
import com.pm.savings.presentation.saving_details.SavingDetailsEvent
import com.pm.savings.presentation.saving_details.SavingDetailsScreen
import com.pm.savings.presentation.saving_details.SavingDetailsViewModel
import com.pm.savings.presentation.savings.SavingsEvent
import com.pm.savings.presentation.savings.SavingsScreen
import com.pm.savings.presentation.savings.SavingsViewModel
import com.pm.savings.presentation.stats.StatsScreen
import com.pm.savings.presentation.stats.StatsViewModel
import com.pm.savings.presentation.wallet_details.WalletDetailsEvent
import com.pm.savings.presentation.wallet_details.WalletDetailsScreen
import com.pm.savings.presentation.wallet_details.WalletDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var state: HomeState

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            state.wallets.isEmpty()
        }

        setContent {
            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            state = viewModel.state.collectAsState().value

            var snackbarMessage by remember {
                mutableStateOf("")
            }

            LaunchedEffect(key1 = snackbarMessage) {
                if (snackbarMessage.isNotBlank()) {
                    snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        duration = SnackbarDuration.Short
                    )
                    snackbarMessage = ""
                }
            }

            Scaffold(
                containerColor = Constants.BACKGROUND_COLOR,
                bottomBar = { BottomBar(navController = navController) },
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
            ) { paddingValues ->
                FinanceRoot(
                    paddingValues = paddingValues,
                    context = applicationContext,
                    navController = navController,
                    showSnackbar = { snackbarMessage = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FinanceRoot(
    paddingValues: PaddingValues,
    context: Context,
    navController: NavController,
    showSnackbar: (String) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val keyboardController = LocalSoftwareKeyboardController.current
    var statusBarColor by remember { mutableStateOf(Constants.BACKGROUND_COLOR) }
    var darkIcons by remember { mutableStateOf(true) }

    LaunchedEffect(statusBarColor, darkIcons) {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = darkIcons
        )
    }

    systemUiController.setNavigationBarColor(Color.White)

    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController as NavHostController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Constants.BACKGROUND_COLOR
            darkIcons = true

            HomeScreen(state = state, onEvent = { event ->
                when (event) {
                    HomeEvent.OnAddOperationClick -> {
                        navController.navigate("${Routes.OPERATION_DETAILS}/${-1}")
                    }

                    HomeEvent.OnAddWalletClick -> {
                        navController.navigate("${Routes.WALLET_DETAILS}/${-1}")
                    }

                    is HomeEvent.OnWalletClick -> {
                        navController.navigate("${Routes.WALLET_DETAILS}/${event.id}")
                    }

                    is HomeEvent.OnOperationClick -> {
                        navController.navigate("${Routes.OPERATION_DETAILS}/${event.id}")
                    }
                }
            })
        }

        composable(Routes.SAVINGS) {
            val viewModel = hiltViewModel<SavingsViewModel>()
            val savings by viewModel.savings.collectAsState()

            statusBarColor = Constants.BACKGROUND_COLOR
            darkIcons = true

            SavingsScreen(savings = savings, onEvent = { event ->
                when (event) {
                    SavingsEvent.OnAddClick -> {
                        navController.navigate("${Routes.SAVING_DETAILS}/${-1L}")
                    }

                    is SavingsEvent.OnSavingClick -> {
                        navController.navigate("${Routes.SAVING_DETAILS}/${event.id}")
                    }
                }
            })
        }

        composable(
            route = Routes.SAVING_DETAILS + "/{savingId}",
            arguments = listOf(navArgument("savingId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val savingId = backStackEntry.arguments?.getLong("savingId") ?: -1L
            val viewModel = hiltViewModel<SavingDetailsViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Color(state.selectedColor)
            darkIcons = false

            LaunchedEffect(key1 = savingId) {
                viewModel.onEvent(SavingDetailsEvent.GetSavingId(savingId))
            }

            LaunchedEffect(key1 = true) {
                viewModel.uiChannel.collect { event ->
                    when (event) {
                        UiEvent.PopBackStack -> {
                            navController.popBackStack()
                        }

                        is UiEvent.ShowSnackbar -> {
                            keyboardController?.hide()
                            showSnackbar(event.uiText.asString(context))
                        }

                        else -> Unit
                    }
                }
            }

            SavingDetailsScreen(state = state, onEvent = { viewModel.onEvent(it) })
        }

        composable(
            route = Routes.OPERATION_DETAILS + "/{operationId}",
            arguments = listOf(navArgument("operationId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val operationId = backStackEntry.arguments?.getLong("operationId") ?: -1L

            val viewModel = hiltViewModel<OperationDetailsViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Color(state.color)
            darkIcons = false

            LaunchedEffect(key1 = operationId) {
                viewModel.onEvent(OperationDetailsEvent.GetOperationId(operationId))
            }

            LaunchedEffect(key1 = true) {
                viewModel.uiChannel.collect { event ->
                    when (event) {
                        UiEvent.PopBackStack -> {
                            navController.popBackStack()
                        }

                        is UiEvent.ShowSnackbar -> {
                            keyboardController?.hide()
                            showSnackbar(event.uiText.asString(context))
                        }

                        else -> Unit
                    }
                }
            }

            OperationDetailsScreen(state = state, onEvent = { event ->
                when (event) {
                    is OperationDetailsEvent.PopBackStack -> {
                        navController.navigate(Routes.HOME)
                    }

                    else -> viewModel.onEvent(event)
                }
            })
        }

        composable(
            route = Routes.WALLET_DETAILS + "/{walletId}",
            arguments = listOf(navArgument("walletId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val walletId = backStackEntry.arguments?.getLong("walletId") ?: -1L

            val viewModel = hiltViewModel<WalletDetailsViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Color(state.color)
            darkIcons = false

            LaunchedEffect(key1 = walletId) {
                viewModel.onEvent(WalletDetailsEvent.GetWalletId(walletId))
            }

            LaunchedEffect(key1 = true) {
                viewModel.uiChannel.collect { event ->
                    when (event) {
                        UiEvent.PopBackStack -> {
                            navController.popBackStack()
                        }

                        is UiEvent.ShowSnackbar -> {
                            keyboardController?.hide()
                            showSnackbar(event.uiText.asString(context))
                        }

                        is UiEvent.NavigateTo -> Unit
                    }
                }
            }

            WalletDetailsScreen(
                state = state, onEvent = { viewModel.onEvent(it) }
            )
        }

        composable(Routes.STATS) {
            val viewModel = hiltViewModel<StatsViewModel>()
            val stateList by viewModel.stateListFlow.collectAsState()
            val currentPage = viewModel.initialPage

            statusBarColor = Constants.BACKGROUND_COLOR
            darkIcons = true

            StatsScreen(
                initPage = currentPage,
                stateList = stateList,
                onEvent = { viewModel.onEvent(it) }
            )
        }

        composable(Routes.PROFILE) {
            val viewModel = hiltViewModel<ProfileViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Constants.BACKGROUND_COLOR
            darkIcons = true

            ProfileScreen(
                state = state, onEvent = { event ->
                    when (event) {
                        ProfileEvent.OnAddNewClick -> {
                            navController.navigate("${Routes.CATEGORY_DETAILS}/${-1L}")
                        }

                        is ProfileEvent.OnCategoryCLick -> {
                            navController.navigate("${Routes.CATEGORY_DETAILS}/${event.categoryId}")
                        }

                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }

        composable(
            route = Routes.CATEGORY_DETAILS + "/{categoryId}",
            arguments = listOf(navArgument("categoryId") {
                type = NavType.LongType
                defaultValue = -1L
            })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: -1L

            val viewModel = hiltViewModel<CategoryDetailsViewModel>()
            val state by viewModel.state.collectAsState()

            statusBarColor = Color(state.color)
            darkIcons = false

            LaunchedEffect(key1 = categoryId) {
                viewModel.onEvent(CategoryDetailsEvent.GetCategoryId(categoryId))
            }

            LaunchedEffect(key1 = true) {
                viewModel.uiChannel.collect { event ->
                    when (event) {
                        UiEvent.PopBackStack -> {
                            navController.popBackStack()
                        }

                        is UiEvent.ShowSnackbar -> {
                            keyboardController?.hide()
                            showSnackbar(event.uiText.asString(context))
                        }

                        else -> Unit
                    }
                }
            }

            CategoryDetailScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        CategoryDetailsEvent.OnBackClick -> {
                            navController.popBackStack()
                        }

                        else -> {
                            viewModel.onEvent(event)
                        }
                    }
                }
            )
        }
    }
}
