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

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.BOOK) {
            BookingScreen(
                selectedFlightId = "F001",
                airline = "Air Tees",
                from = "Newcastle",
                to = "London",
                date = "2025-11-10",
                price = 49.99,
                vm = bookingVM,
                onBooked = { navController.navigate(Routes.MYBOOKINGS) }
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
//NavHost(
//navController = navController,
//startDestination = Routes.SPLASH
//) {
//
//    composable(Routes.BOOK) {
//        BookingScreen(
//            selectedFlightId = "F001",
//            airline = "Air Tees",
//            from = "Newcastle",
//            to = "London",
//            date = "2025-11-10",
//            price = 49.99,
//            vm = bookingVM,
//            onBooked = { navController.navigate(Routes.MYBOOKINGS) }
//        )
//    }
//
//    composable(Routes.MYBOOKINGS) {
//        MyBookingsScreen(
//            vm = bookingVM,
//            onBack = { navController.popBackStack() }
//        )
//    }
//}
