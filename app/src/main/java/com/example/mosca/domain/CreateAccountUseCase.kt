package com.example.mosca.domain

import android.util.Log
import com.example.mosca.data.network.AuthenticationService
import com.example.mosca.data.network.UserService
import com.example.mosca.data.response.LoginResult
import com.example.mosca.ui.screen.register.model.UserRegisterModel
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    suspend operator fun invoke(userRegisterModel: UserRegisterModel): Boolean{
        return when (authenticationService.createAccount(
            userRegisterModel.email,
            userRegisterModel.password
        )) {
            LoginResult.Error -> false
            LoginResult.Success -> userService.createUserTable(userRegisterModel)
        }
    }
}