package com.example.mosca.moscaLogin.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _isRegisterEnable = MutableLiveData<Boolean>()
    val isRegisterEnable: LiveData<Boolean> = _isRegisterEnable

    fun onRegisterChanged(user: UserRegisterModel) {
        _email.value = user.email
        _password.value = user.password
        _confirmPassword.value = user.confirmPassword
        _isRegisterEnable.value = enableRegister(UserRegisterModel(user.email,user.password,user.confirmPassword))
    }

    private fun enableRegister(user: UserRegisterModel): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(user.email).matches() &&
                user.password.length > 6 && user.password == user.confirmPassword
    }

    fun onRegister() {
        _email.value = ""
        _password.value = ""
        _confirmPassword.value = ""
        _isRegisterEnable.value = false
    }
}