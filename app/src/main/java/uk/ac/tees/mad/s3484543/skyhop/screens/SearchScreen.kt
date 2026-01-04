package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    vm: SearchViewModel,
    onSearch: () -> Unit,
    onProfile: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Flights") },
                actions = {
                    IconButton(onClick = onProfile) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedTextField(
                value = vm.origin.value,
                onValueChange = { vm.origin.value = it },
                label = { Text("Origin (City or Airport Code)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vm.destination.value,
                onValueChange = { vm.destination.value = it },
                label = { Text("Destination (City or Airport Code)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vm.date.value,
                onValueChange = { vm.date.value = it },
                label = { Text("DD-MM-YYYY") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = vm.passengers.value,
                onValueChange = { vm.passengers.value = it },
                label = { Text("Passengers") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    vm.doSearch()
                    onSearch()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Flights")
            }
        }
    }
}

