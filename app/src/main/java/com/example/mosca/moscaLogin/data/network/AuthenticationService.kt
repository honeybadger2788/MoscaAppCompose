package com.example.mosca.moscaLogin.data.network

import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthenticationService @Inject constructor(
    private val firebase: FirebaseClient
) {

    suspend fun createAccount(email: String, password: String): AuthResult? {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun login(email: String, password: String): AuthResult? {
        return firebase.auth.signInWithEmailAndPassword(email, password).await()
    }
}