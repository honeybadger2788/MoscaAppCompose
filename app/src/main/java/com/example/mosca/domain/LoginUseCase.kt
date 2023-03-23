package com.example.mosca.domain

import com.example.mosca.data.network.AuthenticationService
import com.example.mosca.data.response.LoginResult
import com.example.mosca.ui.screen.login.model.UserLoginModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    suspend operator fun invoke(userLoginModel: UserLoginModel): LoginResult =
        authenticationService.login(userLoginModel.email, userLoginModel.password)
}