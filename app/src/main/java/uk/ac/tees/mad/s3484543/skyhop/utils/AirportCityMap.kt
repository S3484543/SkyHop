package uk.ac.tees.mad.s3484543.skyhop.utils

object AirportCityMap {

    private val cityToIata = mapOf(
        "london" to "LHR",
        "delhi" to "DEL",
        "mumbai" to "BOM",
        "chennai" to "MAA",
        "hyderabad" to "HYD",
        "bangalore" to "BLR",
        "new york" to "JFK",
        "los angeles" to "LAX",
        "paris" to "CDG",
        "dubai" to "DXB"
    )

    fun toIata(city: String): String {
        return cityToIata[city.lowercase()] ?: city.uppercase()
    }
}
