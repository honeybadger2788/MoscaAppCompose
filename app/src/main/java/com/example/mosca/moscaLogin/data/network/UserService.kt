package com.example.mosca.moscaLogin.data.network

import com.example.mosca.moscaLogin.ui.register.model.UserRegisterModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserService @Inject constructor(
    private val firebase: FirebaseClient
) {
    companion object {
        const val USER_COLLECTION = "users"
    }

    suspend fun createUserTable(userRegisterModel: UserRegisterModel) = runCatching {

        val user = hashMapOf(
            "email" to userRegisterModel.email
        )

        firebase.db
            .collection(USER_COLLECTION)
            .add(user).await()

    }.isSuccess
}