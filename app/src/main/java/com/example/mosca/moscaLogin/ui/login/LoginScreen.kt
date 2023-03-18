package com.example.mosca.moscaLogin.ui.login

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mosca.model.Routes
import com.example.mosca.ui.composable.BrandLogo
import com.example.mosca.ui.composable.CustomTextFieldOutlined
import com.example.mosca.ui.composable.DefaultButton


@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navigationController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        LoginForm(Modifier.padding(vertical = 16.dp), navigationController, loginViewModel)
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
fun LoginForm(modifier: Modifier, navigationController: NavHostController, loginViewModel: LoginViewModel) {
    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column (modifier = modifier) {
        Email(
            email = email,
            onTextChanged = { loginViewModel.onLoginChanged(it, password) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        Password(
            password = password,
            onTextChanged = { loginViewModel.onLoginChanged(email, it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(navigationController, isLoginEnable, loginViewModel)
    }
}

@Composable
fun LoginButton(
    navigationController: NavHostController,
    isLoginEnable: Boolean,
    loginViewModel: LoginViewModel
) {
    DefaultButton(
        text = "INGRESAR", onClick = {
            loginViewModel.onLogin()
            navigationController.navigate(Routes.Home.route)
        },
        enabled = isLoginEnable
    )
}

@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
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
        }
    )
}

@Composable
fun Email(email: String, onTextChanged:(String) -> Unit, modifier: Modifier) {
    CustomTextFieldOutlined(
        label = "Email",
        textValue = email,
        onTextChanged = { onTextChanged(it) },
        trailingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user" ) },
        modifier = modifier
    )
}


