package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

@Composable
fun SearchScreen(vm: SearchViewModel, onSearch: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Search Flights", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = vm.origin,
            onValueChange = { vm.origin = it },
            label = { Text("Origin (e.g. Newcastle)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.destination,
            onValueChange = { vm.destination = it },
            label = { Text("Destination (e.g. London)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.date,
            onValueChange = { vm.date = it },
            label = { Text("Date (YYYY-MM-DD)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.passengers.toString(),
            onValueChange = { vm.passengers = it.toIntOrNull() ?: 1 },
            label = { Text("Passengers") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.width(140.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onSearch() }, modifier = Modifier.fillMaxWidth()) {
            Text("Search")
        }
    }
}