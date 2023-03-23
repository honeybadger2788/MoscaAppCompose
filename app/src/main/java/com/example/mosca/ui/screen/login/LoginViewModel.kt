package com.example.mosca.ui.screen.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mosca.core.Event
import com.example.mosca.data.response.LoginResult.Error
import com.example.mosca.data.response.LoginResult.Success
import com.example.mosca.domain.LoginUseCase
import com.example.mosca.ui.screen.login.model.UserLoginModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) :ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>>
        get() = _navigateToHome

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email,password)
    }

    private fun enableLogin(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6
    }

    fun onLogin(user: UserLoginModel) {
        viewModelScope.launch {
            when(loginUseCase(user)) {
                Success -> {
                    _showError.value = false
                    _email.value = ""
                    _password.value = ""
                    _isLoginEnable.value = false
                    _navigateToHome.value = Event(true)
                }
                Error -> {
                    _showError.value = true
                    _isLoginEnable.value = false
                }
            }
        }
    }
}