package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.model.Booking
import uk.ac.tees.mad.s3484543.skyhop.viewmodel.BookingViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    selectedFlightId: String,
    airline: String,
    from: String,
    to: String,
    date: String,
    price: Double,
    vm: BookingViewModel,
    onConfirmBooking: (Booking) -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Flight: $from → $to")
            Text("Time: $date")
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Passenger Name") })
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    val booking = Booking(
                       // id = UUID.randomUUID().toString(),
                        flightId = selectedFlightId,
                        airline = airline,
                        from = from,
                        to = to,
                        date = date,
                        passengerName = name,
                        passengerEmail = email,
                        price = price
                    )
                    vm.insert(booking)
                    onConfirmBooking(booking) // Pass booking to next screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Booking (£${String.format(Locale.UK, "%.2f", price)})")
            }
        }
    }
}
