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
import com.example.mosca.moscaHome.ui.HomeScreen
import com.example.mosca.moscaHome.ui.HomeViewModel
import com.example.mosca.moscaLogin.ui.login.LoginScreen
import com.example.mosca.moscaLogin.ui.login.LoginViewModel
import com.example.mosca.moscaLogin.ui.register.RegisterScreen
import com.example.mosca.model.Routes
import com.example.mosca.moscaLogin.ui.register.RegisterViewModel
import com.example.mosca.ui.theme.MoscaTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
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
                        composable(Routes.Login.route){ LoginScreen(loginViewModel,navigationController) }
                        composable(Routes.Register.route){ RegisterScreen(registerViewModel,navigationController) }
                        composable(Routes.Home.route){ HomeScreen(homeViewModel, navigationController) }
                    }
                }
            }
        }
    }
}