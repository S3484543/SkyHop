package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.s3484543.skyhop.datastore.ProfileDataStore
import uk.ac.tees.mad.s3484543.skyhop.datastore.ProfileData
import uk.ac.tees.mad.s3484543.skyhop.utils.base64ToBitmap
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
    vm: BookingViewModel,
    onConfirmBooking: () -> Unit,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val profileStore = remember { ProfileDataStore(context) }
    val profile by profileStore.profileFlow.collectAsState(
        initial = ProfileData("", "", "")
    )

    val profileBitmap = base64ToBitmap(profile.photoBase64)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ✅ PROFILE HEADER
            if (profileBitmap != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        bitmap = profileBitmap!!.asImageBitmap(),
                        contentDescription = "Profile Photo",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(profile.name, style = MaterialTheme.typography.titleMedium)
                        Text(profile.email, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            Divider()

            Text("Flight: $from → $to")
            Text("Date: $date")
            Text("Price: £$price")

            Button(
                onClick = onConfirmBooking,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm Booking")
            }
        }
    }
}
