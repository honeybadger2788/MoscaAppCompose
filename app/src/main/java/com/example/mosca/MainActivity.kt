package com.example.mosca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mosca.home.ui.HomeScreen
import com.example.mosca.home.ui.HomeViewModel
import com.example.mosca.login.ui.LoginScreen
import com.example.mosca.register.ui.RegisterScreen
import com.example.mosca.routes.Routes
import com.example.mosca.ui.theme.MoscaTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoscaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Login.route){
                        composable(Routes.Login.route){ LoginScreen(navigationController) }
                        composable(Routes.Register.route){ RegisterScreen(navigationController) }
                        composable(Routes.Home.route){ HomeScreen(homeViewModel, navigationController) }
                    }
                }
            }
        }
    }
}