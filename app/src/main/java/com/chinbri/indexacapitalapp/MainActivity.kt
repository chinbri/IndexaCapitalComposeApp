package com.chinbri.indexacapitalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chinbri.indexacapitalapp.ui.screens.AccountScreen
import com.chinbri.indexacapitalapp.ui.screens.MainScreen
import com.chinbri.indexacapitalapp.ui.theme.IndexaCapitalAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IndexaCapitalAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") { MainScreen(innerPadding, navController) }
                        composable("accountScreen/{accountNumber}") { backStackEntry ->
                            val accountNumber = backStackEntry.arguments?.getString("accountNumber")
                            AccountScreen(innerPadding, accountNumber)
                        }
                    }
                }

            }
        }
    }
}
