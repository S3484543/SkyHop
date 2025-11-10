package uk.ac.tees.mad.s3484543.skyhop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import uk.ac.tees.mad.s3484543.skyhop.data.MockFlightsRepository
import uk.ac.tees.mad.s3484543.skyhop.model.Flight

class SearchViewModel : ViewModel() {
    var origin by mutableStateOf("")
    var destination by mutableStateOf("")
    var date by mutableStateOf("")
    var passengers by mutableStateOf(1)

    var results by mutableStateOf<List<Flight>>(emptyList())
        private set

    fun doSearch() {
        if (origin.isBlank() || destination.isBlank() || date.isBlank()) {
            results = emptyList()
            return
        }
        results = MockFlightsRepository.searchFlights(origin.trim(), destination.trim(), date.trim())
    }

    fun clearResults() { results = emptyList() }
}