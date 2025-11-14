package uk.ac.tees.mad.s3484543.skyhop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ac.tees.mad.s3484543.skyhop.model.Booking

@Database(entities = [Booking::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookingDao(): BookingDao
}
