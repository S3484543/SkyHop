package uk.ac.tees.mad.s3484543.skyhop.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import uk.ac.tees.mad.s3484543.skyhop.data.AppDatabase
import uk.ac.tees.mad.s3484543.skyhop.data.BookingRepository
import uk.ac.tees.mad.s3484543.skyhop.model.Booking

class BookingViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "skyhop.db"
    ).build()

    private val repo = BookingRepository(db.bookingDao())

    val bookings = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insert(booking: Booking) = viewModelScope.launch {
        repo.insert(booking)
    }

    fun delete(booking: Booking) = viewModelScope.launch {
        repo.delete(booking)
    }
}
