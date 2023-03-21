package com.example.mosca.moscaLogin.data.response

sealed class LoginResult {
    object Error: LoginResult()
    object Success: LoginResult()
}