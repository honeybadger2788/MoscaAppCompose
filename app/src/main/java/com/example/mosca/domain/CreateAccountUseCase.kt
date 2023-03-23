package com.example.mosca.domain

import com.example.mosca.data.network.AuthenticationService
import com.example.mosca.data.network.UserService
import com.example.mosca.ui.screen.register.model.UserRegisterModel
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    suspend operator fun invoke(userRegisterModel: UserRegisterModel): Boolean{
        val accountCreated =
            authenticationService.createAccount(
                userRegisterModel.email,
                userRegisterModel.password
            ) != null
        return if (accountCreated) {
            userService.createUserTable(userRegisterModel)
        } else {
            false
        }
    }
}