package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    price: Double,
    onPaid: () -> Unit,
    onBack: () -> Unit
) {
    var showSuccess by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Payment") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Flight Payment", style = MaterialTheme.typography.titleLarge)

                Text(
                    "Amount to pay: £${String.format(Locale.UK, "%.2f", price)}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Button(
                    onClick = { showSuccess = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Payment")
                }

                OutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancel")
                }
            }

            if (showSuccess) {
                SimpleSuccessPopup(
                    onDone = onPaid
                )
            }
        }
    }
}
@Composable
fun SimpleSuccessPopup(onDone: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = onDone) {
                Text("View Ticket")
            }
        },
        title = { Text("Payment Successful 🎉") },
        text = { Text("Your payment was completed successfully.") }
    )
}
