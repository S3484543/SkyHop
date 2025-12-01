package uk.ac.tees.mad.s3484543.skyhop.ui.components

sealed class BottomNavItem(val route: String, val label: String) {
    object Search : BottomNavItem("search", "Search")
    object Results : BottomNavItem("results", "Results")
    object Bookings : BottomNavItem("mybookings", "Bookings")
}