package uk.ac.tees.mad.s3484543.skyhop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import uk.ac.tees.mad.s3484543.skyhop.navigation.AppNavHost
import uk.ac.tees.mad.s3484543.skyhop.ui.theme.SkyHopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkyHopTheme {
                AppNavHost()
            }
        }
    }
}
