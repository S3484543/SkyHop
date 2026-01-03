package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.Icons
import uk.ac.tees.mad.s3484543.skyhop.model.Flight
import uk.ac.tees.mad.s3484543.skyhop.ui.components.FlightCard
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(
    vm: SearchViewModel,
    onBack: () -> Unit,
    onFlightSelected: (Flight) -> Unit
) {
    val flights by vm.results.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Available Flights") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(flights) { flight ->
                FlightCard(
                    flight = flight,
                    onClick = { onFlightSelected(flight) }
                )
            }
        }
    }
}