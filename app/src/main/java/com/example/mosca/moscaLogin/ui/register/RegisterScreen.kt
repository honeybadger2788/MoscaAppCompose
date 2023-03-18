package com.example.mosca.moscaLogin.ui.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mosca.model.Routes
import com.example.mosca.ui.composable.BrandLogo
import com.example.mosca.ui.composable.CustomTextFieldOutlined
import com.example.mosca.ui.composable.DefaultButton


@Composable
fun RegisterScreen(registerViewModel: RegisterViewModel, navigationController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        RegisterForm(Modifier.padding(vertical = 16.dp), registerViewModel, navigationController)
    }
}

@Composable
fun RegisterForm(
    modifier: Modifier,
    registerViewModel: RegisterViewModel,
    navigationController: NavHostController
) {
    val email: String by registerViewModel.email.observeAsState(initial = "")
    val password: String by registerViewModel.password.observeAsState(initial = "")
    val confirmPassword: String by registerViewModel.confirmPassword.observeAsState(initial = "")
    val isRegisterEnable: Boolean by registerViewModel.isRegisterEnable.observeAsState(initial = false)

    Column (modifier = modifier) {
        Email(
            email,
            { registerViewModel.onRegisterChanged(it, password, confirmPassword) },
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        com.example.mosca.moscaLogin.ui.login.Password(
            password = password,
            onTextChanged = { registerViewModel.onRegisterChanged(email, it, confirmPassword) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(8.dp))
        Password(
            password = confirmPassword,
            label = "Confirmar contraseña",
            onTextChanged = { registerViewModel.onRegisterChanged(email, password, it) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(16.dp))
        RegisterButton(isRegisterEnable, navigationController, registerViewModel)
    }
}

@Composable
fun RegisterButton(
    isLoginEnable: Boolean,
    navigationController: NavHostController,
    registerViewModel: RegisterViewModel
) {
    DefaultButton(
        text = "REGISTRAR",
        onClick = {
            registerViewModel.onRegister()
            navigationController.navigate(Routes.Login.route)
        },
        enabled = isLoginEnable
    )
}

@Composable
fun Password(password: String, label: String = "Contraseña" , onTextChanged: (String) -> Unit, modifier: Modifier) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    CustomTextFieldOutlined(
        label = label,
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
fun Email(email: String, onTextChanged: (String)->Unit ,modifier: Modifier) {
    CustomTextFieldOutlined(
        label = "Email",
        textValue = email,
        onTextChanged = { onTextChanged(it) },
        trailingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user" ) },
        modifier = modifier
    )
}
