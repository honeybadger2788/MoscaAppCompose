package com.example.mosca.domain

import com.example.mosca.data.network.AuthenticationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authorizationService: AuthenticationService
) {
    operator fun invoke(): Flow<Boolean> = authorizationService.currentUser
}