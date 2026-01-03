package uk.ac.tees.mad.s3484543.skyhop.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.s3484543.skyhop.model.Flight
import uk.ac.tees.mad.s3484543.skyhop.network.RetrofitInstance
import uk.ac.tees.mad.s3484543.skyhop.utils.AirportCityMap

class SearchViewModel : ViewModel() {

    var origin = mutableStateOf("")
    var destination = mutableStateOf("")
    var date = mutableStateOf("")
    var passengers = mutableStateOf("1")
    var selectedFlight: Flight? = null


    private val _results = MutableStateFlow<List<Flight>>(emptyList())
    val results: StateFlow<List<Flight>> = _results

    fun passengerCount(): Int =
        passengers.value.toIntOrNull() ?: 1

    fun doSearch() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFlights(
                    apiKey = "354d3fcd6c3a7ef38c34d2d710b3a31c",
                    from = AirportCityMap.toIata(origin.value),
                    to = AirportCityMap.toIata(destination.value)
                )

                _results.value = response.data.map {flightData ->
                    Flight(
                        id = "${flightData.departure?.iata}-${flightData.arrival?.iata}",
                        airline = flightData.airline?.name ?: "Unknown",
                        from = flightData.departure?.iata ?: "",
                        to = flightData.arrival?.iata ?: "",
                        departTime = flightData.departure?.scheduled ?: "",
                        arriveTime = flightData.arrival?.scheduled ?: "N/A",
                        duration = "2h 30m",
                        price = 300.0
                    )
                }
            } catch (_: Exception) {
                _results.value = emptyList()
            }
        }
    }
}
