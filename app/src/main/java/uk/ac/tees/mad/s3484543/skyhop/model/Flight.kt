package uk.ac.tees.mad.s3484543.skyhop.model

data class Flight(
    val id: String,
    val airline: String,
    val from: String,
    val to: String,
    val departTime: String,
    val arriveTime: String,
    val duration: String,
    val price: Double
)