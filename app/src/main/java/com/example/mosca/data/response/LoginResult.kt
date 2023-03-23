package com.example.mosca.data.response

sealed class LoginResult {
    object Error: LoginResult()
    object Success: LoginResult()
}