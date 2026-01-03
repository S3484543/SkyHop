package uk.ac.tees.mad.s3484543.skyhop.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Amount to pay: £${"%.2f".format(price)}")

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { showSuccess = true }
            ) {
                Text("Select Payment")
            }
        }

        if (showSuccess) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {
                    Button(onClick = onPaid) {
                        Text("View Ticket")
                    }
                },
                title = { Text("Payment Successful 🎉") },
                text = { Text("Your booking has been confirmed.") }
            )
        }
    }
}