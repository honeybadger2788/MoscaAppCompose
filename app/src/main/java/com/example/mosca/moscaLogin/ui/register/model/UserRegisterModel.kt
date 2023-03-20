package com.example.mosca.moscaLogin.ui.register.model

data class UserRegisterModel(val email: String, val password: String, val confirmPassword: String) {
    fun isNotEmpty() =
        email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
}