package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import uk.ac.tees.mad.s3484543.skyhop.ui.components.FlightCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(vm: SearchViewModel, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Results") }, navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
            })
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (vm.results.isEmpty()) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("No results found for: ${vm.origin} → ${vm.destination}")
                }
            } else {
                LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(vm.results) { flight ->
                        FlightCard(flight = flight)
                    }
                }
            }
        }
    }
}