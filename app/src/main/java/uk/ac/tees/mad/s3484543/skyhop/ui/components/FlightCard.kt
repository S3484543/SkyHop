package uk.ac.tees.mad.s3484543.skyhop.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.model.Flight
import java.util.Locale

@Composable
fun FlightCard(flight: Flight) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("${flight.airline} • ${flight.id}")
            Spacer(modifier = Modifier.height(6.dp))
            Text("${flight.from} → ${flight.to}  (${flight.duration})")
            Spacer(modifier = Modifier.height(6.dp))
            Text("Depart: ${flight.departTime}  •  Arrive: ${flight.arriveTime}")
            Spacer(modifier = Modifier.height(6.dp))
            Text("Price: £${String.format(Locale.UK, "%.2f", flight.price)}")
        }
    }
}