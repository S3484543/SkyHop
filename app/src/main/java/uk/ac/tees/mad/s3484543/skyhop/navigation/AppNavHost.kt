package uk.ac.tees.mad.s3484543.skyhop.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3484543.skyhop.screens.*
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.BookingViewModel
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

object Routes {
    const val SPLASH = "splash"
    const val SEARCH = "search"
    const val RESULTS = "results"
    const val BOOK = "book"
    const val PAYMENT = "payment"
    const val TICKET = "ticket"
    const val MYBOOKINGS = "mybookings"
    const val PROFILE = "profile"
    const val SETTINGS = "settings"
}

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val searchVM: SearchViewModel = viewModel()
    val bookingVM: BookingViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen {
                navController.navigate(Routes.SEARCH) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }

        composable(Routes.SEARCH) {
            SearchScreen(
                vm = searchVM,
                onSearch = { navController.navigate(Routes.RESULTS) },
                onProfile = { navController.navigate(Routes.PROFILE) }
            )
        }

        composable(Routes.RESULTS) {
            ResultsScreen(
                vm = searchVM,
                onBack = { navController.popBackStack() },
                onFlightSelected = { flight ->
                    searchVM.selectedFlight = flight
                    navController.navigate(Routes.BOOK)
                }
            )
        }

        composable(Routes.BOOK) {
            val flight = searchVM.selectedFlight
            if (flight != null) {
                BookingScreen(
                    selectedFlightId = flight.id,
                    airline = flight.airline,
                    from = flight.from,
                    to = flight.to,
                    date = flight.departTime,
                    price = flight.price,
                    passengerCount = searchVM.passengerCount(),
                    vm = bookingVM,
                    onConfirmBooking = {
                        navController.navigate(Routes.PAYMENT)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.PAYMENT) {
            val booking = bookingVM.getLatestBooking()
            booking?.let {
                PaymentScreen(
                    price = it.price,
                    onPaid = {
                        navController.navigate(Routes.TICKET)
                    },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.TICKET) {
            val booking = bookingVM.getLatestBooking()
            booking?.let {
                TicketScreen(
                    booking = it,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.MYBOOKINGS) {
            MyBookingsScreen(
                vm = bookingVM,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
