package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.model.Booking
import uk.ac.tees.mad.s3484543.skyhop.model.Passenger
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    selectedFlightId: String,
    airline: String,
    from: String,
    to: String,
    date: String,
    price: Double,
    passengerCount: Int,
    vm: BookingViewModel,
    onConfirmBooking: (Booking) -> Unit,
    onBack: () -> Unit
) {
    val passengers = remember {
        mutableStateListOf<Passenger>().apply {
            repeat(passengerCount) {
                add(Passenger())
            }
        }
    }

    val totalPrice = price * passengerCount

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Airline: $airline")
            Text("Route: $from → $to")
            Text("Date: $date")
            Text("Passengers: $passengerCount")

            Divider()

            passengers.forEachIndexed { index, passenger ->

                Text(
                    text = "Passenger ${index + 1}",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = passenger.name,
                    onValueChange = { newValue ->
                        passengers[index] = passenger.copy(name = newValue)
                    },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = passenger.email,
                    onValueChange = { newValue ->
                        passengers[index] = passenger.copy(email = newValue)
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Divider()
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val booking = Booking(
                        flightId = selectedFlightId,
                        airline = airline,
                        from = from,
                        to = to,
                        date = date,
                        passengerName = passengers.joinToString { it.name },
                        passengerEmail = passengers.joinToString { it.email },
                        passengerCount = passengerCount,
                        price = totalPrice
                    )

                    vm.insert(booking)
                    onConfirmBooking(booking)
                }
            ) {
                Text("Confirm (£${"%.2f".format(totalPrice)})")
            }
        }
    }
}
