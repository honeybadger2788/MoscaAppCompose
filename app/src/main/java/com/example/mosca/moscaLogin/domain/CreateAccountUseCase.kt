package com.example.mosca.moscaLogin.domain

import com.example.mosca.moscaLogin.data.network.AuthenticationService
import com.example.mosca.moscaLogin.data.network.UserService
import com.example.mosca.moscaLogin.ui.register.model.UserRegisterModel
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