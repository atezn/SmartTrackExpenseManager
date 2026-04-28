package com.example.smarttrackexpensemanager

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.smarttrackexpensemanager.ui.theme.SmartTrackExpenseManagerTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlin.math.cos
import kotlin.math.sin
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp

val AppDarkBg = Color(0xFF100B20)
val SurfaceColor = Color(0xFF2A2438)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Your Theme setup
            MaterialTheme{
                Surface(modifier = Modifier.fillMaxSize()) {
                    // This is your main entry point
                    SmartTrackApp()
            }
        }
    }
}

@Composable
fun SmartTrackApp() {
    val navController = rememberNavController()
    MaterialTheme(colorScheme = darkColorScheme(background = AppDarkBg, surface = SurfaceColor)) {
        NavHost(navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("scan") { ScanPage(navController) }
            composable("fill") { FillingPage(navController) }
            composable("history") { HistoryPage(navController) }
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(AppDarkBg).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("HOME PAGE / DASHBOARD", color = Color.White, modifier = Modifier.padding(top = 40.dp))

        Spacer(modifier = Modifier.weight(1f))

        // Wrapped in clickable to go to history
        Box(contentAlignment = Alignment.Center, modifier = Modifier.clickable { navController.navigate("history") }) {
            Canvas(modifier = Modifier.size(300.dp)) { val slices = listOf(0.2f to Color.Cyan, 0.2f to Color(0xFFE57373), 0.2f to Color(0xFF9575CD), 0.2f to Color(0xFFEF5350), 0.2f to Color(0xFF4682B4))
                var startAngle = 0f
                slices.forEach { (sweep, color) ->
                    drawArc(color, startAngle, sweep * 360f, false, style = Stroke(width = 60f), size = Size(size.width, size.height))
                    startAngle += sweep * 360f
                } }
            Text("CLICK FOR\nDETAILED\nEXPENSES", color = Color.White, textAlign = TextAlign.Center)
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("SCAN RECEIPT", color = Color.White)
                IconButton(onClick = { navController.navigate("scan") }, modifier = Modifier.size(70.dp).background(Color.LightGray, CircleShape)) { Text("▢") }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("ADD EXPENSE", color = Color.White)
                IconButton(onClick = { navController.navigate("fill") }, modifier = Modifier.size(70.dp).background(Color.Gray, CircleShape)) { Text("+") }
            }
        }
    }
}

@Composable
fun ScanPage(navController: NavController) {
    Column(Modifier.fillMaxSize().background(AppDarkBg).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        // ... header ...
        Box(Modifier.weight(1f).fillMaxWidth().padding(32.dp).border(2.dp, Color.White), contentAlignment = Alignment.Center) {
            Text("THE ACTUAL CAMERA FOOTAGE", color = Color.White)
        }
        Text("SCAN RECEIPT", color = Color.White)
        // Fixed: navigate to "fill" instead of "home"
        IconButton(onClick = { navController.navigate("fill") }, modifier = Modifier.size(70.dp).background(Color.LightGray, CircleShape)) { Text("▢") }
    }
}

    // 3. UPDATED FILLING PAGE (Fixed Text Color Visibility)
@Composable
fun FillingPage(navController: NavController) {
    // Set colors for the text field to ensure visibility
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedContainerColor = Color.LightGray,
        unfocusedContainerColor = Color.LightGray
    )

    Column(Modifier.fillMaxSize().background(AppDarkBg).padding(24.dp)) {
        Text("RECEIPT FILLING PAGE", color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally).padding(vertical = 40.dp))

        Text("CATEGORY", color = Color.White)
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("SELECT A CATEGORY") }, colors = fieldColors, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Text("AMOUNT", color = Color.White)
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("ENTER THE AMOUNT") }, colors = fieldColors, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Text("ADDITIONAL DESCRIPTION", color = Color.White)
        OutlinedTextField(value = "", onValueChange = {}, label = { Text("DESCRIPTION") }, colors = fieldColors, modifier = Modifier.fillMaxWidth().height(100.dp))

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { navController.navigate("home") }, modifier = Modifier.fillMaxWidth().height(60.dp)) {
            Text("ADD EXPENSE", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun HistoryPage(navController: NavController) {
    Column(Modifier.fillMaxSize().background(AppDarkBg).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth().background(Color.Gray, RoundedCornerShape(16.dp)).padding(16.dp)) {
            items(10) { index -> Text("# LATEST EXPENSE ${index + 1}", color = Color.White, modifier = Modifier.padding(20.dp)) }
        }
        Text("RETURN TO HOMESCREEN", color = Color.White, modifier = Modifier.padding(8.dp))
        IconButton(onClick = { navController.navigate("home") }, modifier = Modifier.background(Color.LightGray, CircleShape)) { Text("↻") }
    }
}
}