package uk.ac.tees.mad.s3484543.skyhop.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.model.Booking
import java.util.Locale


@Composable
fun BookingCard(
    booking: Booking,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                "${booking.airline} • ${booking.flightId}",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text("${booking.from} → ${booking.to}")
            Text("Date: ${booking.date}")
            Text("Passenger: ${booking.passengerName ?:"_"}")
            Text("Price: £${String.format(Locale.UK, "%.2f", booking.price)}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Delete booking")
            }
        }
    }
}
