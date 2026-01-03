package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.model.Booking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    booking: Booking,
    onBack: () -> Unit
) {
    val names = booking.passengerName.split(",").map { it.trim() }
    val emails = booking.passengerEmail.split(",").map { it.trim() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Ticket") },
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

            Text("Airline: ${booking.airline}", style = MaterialTheme.typography.titleMedium)
            Text("Route: ${booking.from} → ${booking.to}")
            Text("Date: ${booking.date}")
            Text("Passengers: ${booking.passengerCount}")
            Text("Total Paid: £${"%.2f".format(booking.price)}")

            Divider()

            Text("Passenger Details", style = MaterialTheme.typography.titleMedium)

            names.forEachIndexed { index, name ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Passenger ${index + 1}")
                        Text("Name: $name")
                        Text("Email: ${emails.getOrNull(index) ?: "-"}")
                    }
                }
            }
        }
    }
}
