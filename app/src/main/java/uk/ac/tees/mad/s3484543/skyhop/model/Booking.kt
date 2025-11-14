package uk.ac.tees.mad.s3484543.skyhop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val flightId: String,
    val airline: String,
    val from: String,
    val to: String,
    val date: String,
    val passengerName: String,
    val passengerEmail: String,
    val price: Double
)
