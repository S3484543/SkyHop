package uk.ac.tees.mad.s3484543.skyhop.data

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.s3484543.skyhop.model.Booking

class BookingRepository(private val dao: BookingDao) {
    suspend fun insert(booking: Booking) = dao.insertBooking(booking)
    suspend fun delete(booking: Booking) = dao.deleteBooking(booking)
    fun getAll(): Flow<List<Booking>> = dao.getAllBookings()
}