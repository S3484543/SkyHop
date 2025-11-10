package uk.ac.tees.mad.s3484543.skyhop.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.s3484543.skyhop.screens.ResultsScreen
import uk.ac.tees.mad.s3484543.skyhop.screens.SearchScreen
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel
import uk.ac.tees.mad.s3484543.skyhop.screens.SplashScreen


object Routes {
    const val SPLASH = "splash"
    const val SEARCH = "search"
    const val RESULTS = "results"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val searchVM: SearchViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(onTimeout = {
                navController.navigate(Routes.SEARCH) { popUpTo(Routes.SPLASH) { inclusive = true } }
            })
        }
        composable(Routes.SEARCH) {
            SearchScreen(vm = searchVM, onSearch = {
                searchVM.doSearch()
                navController.navigate(Routes.RESULTS)
            })
        }
        composable(Routes.RESULTS) {
            ResultsScreen(vm = searchVM, onBack = { navController.popBackStack() })
        }
    }
}