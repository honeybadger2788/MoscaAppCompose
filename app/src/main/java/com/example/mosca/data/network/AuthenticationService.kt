package com.example.mosca.data.network

import android.util.Log
import com.example.mosca.data.response.LoginResult
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(
    private val firebase: FirebaseClient
) {
    val currentUser: Flow<Boolean> = flow {
        while (true) {
            val user = getCurrentUser()
            emit(user)
            delay(1000)
        }
    }

    suspend fun createAccount(email: String, password: String): LoginResult = runCatching {
        firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    suspend fun login(email: String, password: String): LoginResult = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    fun logout() = firebase.auth.signOut()

    private fun getCurrentUser(): Boolean {
        return firebase.auth.currentUser != null
    }

    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResult.Error
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResult.Success
        }
    }
}