package uk.ac.tees.mad.s3484543.skyhop.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3484543.skyhop.screens.*
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.BookingViewModel
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel
import uk.ac.tees.mad.s3484543.skyhop.model.Booking


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

    NavHost(navController = navController, startDestination = Routes.SPLASH) {

        composable(Routes.SPLASH) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.SEARCH) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(Routes.SEARCH) {
            SearchScreen(vm = searchVM, onSearch = { navController.navigate(Routes.RESULTS) })
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
                    vm = bookingVM,
                    onConfirmBooking = { navController.navigate(Routes.PAYMENT) },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.PAYMENT) {
            val booking: Booking? = bookingVM.getLatestBooking()
            if (booking != null) {
                PaymentScreen(
                    booking = booking,
                    onPaid = { navController.navigate("${Routes.TICKET}/${booking.id}") },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable("${Routes.TICKET}/{bookingId}") { backStackEntry ->
            val bookingId = backStackEntry.arguments?.getString("bookingId") ?: ""
            val booking: Booking? = bookingVM.getBookingById(bookingId)
            if (booking != null) {
                TicketScreen(
                    booking = booking,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable(Routes.MYBOOKINGS) {
            MyBookingsScreen(vm = bookingVM, onBack = { navController.popBackStack() })
        }

        composable(Routes.PROFILE) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
