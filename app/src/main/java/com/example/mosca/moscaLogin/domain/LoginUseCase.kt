package com.example.mosca.moscaLogin.domain

import com.example.mosca.moscaLogin.data.network.AuthenticationService
import com.example.mosca.moscaLogin.data.response.LoginResult
import com.example.mosca.moscaLogin.ui.login.model.UserLoginModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
) {
    suspend operator fun invoke(userLoginModel: UserLoginModel): LoginResult {
        return authenticationService.login(userLoginModel.email, userLoginModel.password)
    }
}