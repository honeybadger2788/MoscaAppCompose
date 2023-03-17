package com.example.mosca.model

sealed class Routes(val route: String) {
    object Login:Routes("login")
    object Register:Routes("register")
    object Home:Routes("home")
}