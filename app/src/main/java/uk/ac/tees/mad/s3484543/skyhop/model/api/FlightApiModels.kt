package uk.ac.tees.mad.s3484543.skyhop.model.api

data class FlightResponse(
    val data: List<ApiFlight>
)

data class ApiFlight(
    val airline: Airline?,
    val departure: Departure?,
    val arrival: Arrival?
)

data class Airline(val name: String?)
data class Departure(val iata: String?, val scheduled: String?)
data class Arrival(val iata: String?, val scheduled: String?)
