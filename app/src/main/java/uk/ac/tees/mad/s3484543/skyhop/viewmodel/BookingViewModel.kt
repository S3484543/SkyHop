package uk.ac.tees.mad.s3484543.skyhop.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.ac.tees.mad.s3484543.skyhop.model.Booking

class BookingViewModel : ViewModel() {

    private val _bookings = MutableStateFlow<List<Booking>>(emptyList())
    val bookings: StateFlow<List<Booking>> = _bookings

    fun insert(booking: Booking) {
        _bookings.value = _bookings.value + booking
    }

    fun getLatestBooking(): Booking? = _bookings.value.lastOrNull()

    fun getBookingById(id: String): Booking? = _bookings.value.find { it.id == id }
}
