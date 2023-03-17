package com.example.mosca.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mosca.R
import com.example.mosca.model.Routes
import com.example.mosca.ui.composable.CustomTextFieldOutlined
import com.example.mosca.ui.composable.DefaultButton


@Composable
fun LoginScreen(navigationController: NavHostController) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        LoginForm(Modifier.padding(vertical = 16.dp), navigationController)
        LoginDivider()
        Text(
            text = "REGISTRATE",
            Modifier
                .padding(8.dp)
                .clickable {
                    navigationController.navigate(Routes.Register.route)
                },
            color = Color(0xff0097a7),
            fontWeight = FontWeight.Bold
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
fun LoginForm(modifier: Modifier, navigationController: NavHostController) {
    Column (modifier = modifier) {
        Email(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Password(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(navigationController)
    }
}

@Composable
fun LoginButton(navigationController: NavHostController) {
    DefaultButton(text = "INGRESAR", onClick = {
        navigationController.navigate(Routes.Home.route)
    }, enabled = true)
}

@Composable
fun Password(modifier: Modifier) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextFieldOutlined(
        label = "Contraseña",
        textValue = password,
        onTextChanged = { password = it },
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
fun Email(modifier: Modifier) {
    var email by rememberSaveable {
        mutableStateOf("")
    }

    CustomTextFieldOutlined(
        label = "Email",
        textValue = email,
        onTextChanged = { email = it },
        trailingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user" ) },
        modifier = modifier
    )
}

@Composable
fun BrandLogo() {
    Image(
        painter = painterResource(id = R.drawable.fly),
        contentDescription = "logo",
        modifier = Modifier.width(64.dp)
    )
}
