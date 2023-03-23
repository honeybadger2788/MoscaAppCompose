package com.example.mosca.domain

import com.example.mosca.data.network.AuthenticationService
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authenticationService: AuthenticationService
)
{
    operator fun invoke() = authenticationService.logout()
}