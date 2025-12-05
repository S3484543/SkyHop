package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.input.KeyboardType
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

@Composable
fun SearchScreen(vm: SearchViewModel, onSearch: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Search Flights",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = vm.origin,
            onValueChange = { vm.origin = it },
            label = { Text("Origin (e.g., London)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = vm.destination,
            onValueChange = { vm.destination = it },
            label = { Text("Destination (e.g., Hyderabad)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = vm.date,
            onValueChange = { vm.date = it },
            label = { Text("DD-MM-YYYY") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = vm.passengers,
            onValueChange = { vm.passengers = it },
            label = { Text("Passengers") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(
            onClick = {
                vm.doSearch()
                onSearch()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }
    }
}