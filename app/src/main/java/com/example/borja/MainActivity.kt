package com.example.borja

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
import com.example.borja.ui.theme.BorjaTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BorjaTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Home> {
                            HomeScreen(onShowGreeting = { typedName ->
                                // pass the name by creating a Greeting route object
                                navController.navigate(Greeting(userName = typedName))
                            })
                        }

                        composable<Greeting> { backStackEntry ->
                            // rebuild the typed Greeting object on this screen
                            val greeting: Greeting = backStackEntry.toRoute()
                            GreetingScreen(
                                userName = greeting.userName,
                                onBack = { navController.navigateUp() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingMessage(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BorjaTheme {
        GreetingMessage("Android")
    }
}
