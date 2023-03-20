package com.example.mosca.moscaLogin.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mosca.moscaLogin.ui.register.model.UserRegisterModel

class RegisterViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> = _confirmPassword

    private val _isRegisterEnable = MutableLiveData<Boolean>()
    val isRegisterEnable: LiveData<Boolean> = _isRegisterEnable

    fun onRegisterChanged(email: String, password: String, confirmPassword: String) {
        _email.value = email
        _password.value = password
        _confirmPassword.value = confirmPassword
        _isRegisterEnable.value = enableRegister(email,password, confirmPassword)
    }

    private fun enableRegister(email: String, password: String, confirmPassword: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                password.length > 6 && password == confirmPassword
    }

    fun onRegister(user: UserRegisterModel) {
        _email.value = ""
        _password.value = ""
        _confirmPassword.value = ""
        _isRegisterEnable.value = false
    }
}