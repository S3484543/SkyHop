package uk.ac.tees.mad.s3484543.skyhop.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3484543.skyhop.screens.BookingScreen
import uk.ac.tees.mad.s3484543.skyhop.screens.MyBookingsScreen
import uk.ac.tees.mad.s3484543.skyhop.screens.ResultsScreen
import uk.ac.tees.mad.s3484543.skyhop.screens.SearchScreen
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel
import uk.ac.tees.mad.s3484543.skyhop.screens.SplashScreen
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.BookingViewModel


object Routes {
    const val SPLASH = "splash"
    const val SEARCH = "search"
    const val RESULTS = "results"
    const val BOOK = "book"
    const val MYBOOKINGS = "mybookings"
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
            SplashScreen(onTimeout = {
                navController.navigate(Routes.SEARCH) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            })
        }

        composable(Routes.SEARCH) {
            SearchScreen(
                vm = searchVM,
                onSearch = { navController.navigate(Routes.RESULTS) }
            )
        }

        composable(Routes.RESULTS) {
            ResultsScreen(
                vm = searchVM,
                onBack = { navController.popBackStack() },
                onFlightSelected = { flight ->
                    // Store the selected flight in SearchViewModel or a shared ViewModel
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
                    onBooked = { navController.navigate(Routes.MYBOOKINGS) }
                )
            }
        }

        composable(Routes.MYBOOKINGS) {
            MyBookingsScreen(
                vm = bookingVM,
                onBack = { navController.popBackStack() }
            )
        }
    }
}