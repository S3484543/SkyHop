package uk.ac.tees.mad.s3484543.skyhop.data

import uk.ac.tees.mad.s3484543.skyhop.model.Flight

object MockFlightsRepository {
    private val flights = listOf(
        Flight("F001","Air Tees","Newcastle","London","07:30","08:45","1h15",49.99),
        Flight("F002","NorthAir","Newcastle","London","09:00","10:15","1h15",59.99),
        Flight("F003","Skyways","Manchester","London","08:00","09:10","1h10",39.99),
        Flight("F004","Air Tees","Newcastle","Edinburgh","11:00","12:00","1h0",29.99),
        Flight("F005","NorthAir","London","Newcastle","18:00","19:15","1h15",52.99)
    )

    fun searchFlights(from: String, to: String, date: String): List<Flight> {
        return flights.filter {
            it.from.equals(from, ignoreCase = true) && it.to.equals(to, ignoreCase = true)
        }
    }
}