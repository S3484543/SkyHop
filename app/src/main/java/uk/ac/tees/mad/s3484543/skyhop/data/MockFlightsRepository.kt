package uk.ac.tees.mad.s3484543.skyhop.data

import uk.ac.tees.mad.s3484543.skyhop.model.Flight

object MockFlightsRepository {

    private val flights = listOf(
        Flight("F001", "Air Tees", "Newcastle", "London", "07:30", "08:45", "1h15", 49.99),
        Flight("F002", "SkyHop Express", "Newcastle", "London", "10:00", "11:20", "1h20", 59.99),
        Flight("F003", "Northern Air", "Manchester", "Edinburgh", "09:15", "10:00", "45m", 39.99),
        Flight("F004", "Air Midlands", "Birmingham", "Paris", "13:45", "16:00", "2h15", 99.99),
        Flight("F005", "EuroSky", "London", "Paris", "06:00", "08:10", "2h10", 89.50)
    )

    fun searchFlights(from: String, to: String): List<Flight> {

        val f = from.trim().lowercase()
        val t = to.trim().lowercase()

        return flights.filter { flight ->
            flight.from.lowercase().contains(f) &&
                    flight.to.lowercase().contains(t)
        }
    }
}