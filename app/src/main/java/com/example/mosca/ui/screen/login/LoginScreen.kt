package com.example.mosca.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import com.example.mosca.model.Routes
import com.example.mosca.ui.screen.login.model.UserLoginModel
import com.example.mosca.ui.composable.BrandLogo
import com.example.mosca.ui.composable.CustomTextFieldOutlined
import com.example.mosca.ui.composable.DefaultButton
import com.example.mosca.ui.composable.ErrorMessage


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navigationController: NavHostController) {
    val lifecycle = LocalLifecycleOwner.current

    loginViewModel.navigateToHome.observe(lifecycle) {
        it.getContentIfNotHandled()?.let {
            navigationController.navigate(Routes.Home.route)
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        LoginForm(Modifier.padding(vertical = 16.dp), loginViewModel)
        LoginDivider()
        Text(
            text = "REGISTRATE",
            Modifier
                .padding(8.dp)
                .clickable {
                    navigationController.navigate(Routes.Register.route)
                },
            color = Color(0xff0097a7),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}


@Composable
fun LoginDivider() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "O",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun LoginForm(modifier: Modifier, loginViewModel: LoginViewModel) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)
    val showError: Boolean by loginViewModel.showError.observeAsState(initial = false)

    Column (modifier = modifier) {
        if (showError)
            ErrorMessage(message = "Algo salió mal")
        Email(
            email = email,
            onTextChanged = { loginViewModel.onLoginChanged(it, password) },
            modifier = Modifier.fillMaxWidth(),
            showError
        )
        Spacer(modifier = Modifier.size(8.dp))
        Password(
            password = password,
            onTextChanged = { loginViewModel.onLoginChanged(email, it) },
            modifier = Modifier.fillMaxWidth(),
            showError
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(
            isLoginEnable,
        ) { loginViewModel.onLogin(UserLoginModel(email, password)) }
    }
}

@Composable
fun LoginButton(
    isLoginEnable: Boolean,
    onClickLogin: () -> Unit
) {

    DefaultButton(
        text = "INGRESAR", onClick = {
            onClickLogin()
        },
        enabled = isLoginEnable
    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier, error: Boolean) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    CustomTextFieldOutlined(
        label = "Contraseña",
        textValue = password,
        onTextChanged = { onTextChanged(it) },
        trailingIcon = {
            val image = if(passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show password")
            }
        },
        modifier = modifier,
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        error = error
    )
}

@Composable
fun Email(email: String, onTextChanged: (String) -> Unit, modifier: Modifier, error: Boolean) {
    CustomTextFieldOutlined(
        label = "Email",
        textValue = email,
        onTextChanged = { onTextChanged(it) },
        trailingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user" ) },
        modifier = modifier,
        error = error
    )
}


