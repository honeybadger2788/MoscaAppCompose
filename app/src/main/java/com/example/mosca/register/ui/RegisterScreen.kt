package com.example.mosca.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mosca.R
import com.example.mosca.model.Routes


@Composable
fun RegisterScreen(navigationController: NavHostController) {
    TopAppBar(backgroundColor = Color.Transparent, elevation = 0.dp) {
        Icon(
            imageVector = Icons.Filled.ArrowBackIos,
            contentDescription = "back",
            tint = Color.Gray,
            modifier = Modifier.padding(start = 16.dp).clickable {
                navigationController.navigate(Routes.Login.route)
            }
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        RegisterForm(Modifier.padding(vertical = 16.dp))
    }
}

@Composable
fun RegisterForm(modifier: Modifier) {
    Column (modifier = modifier) {
        Email(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Password(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        ConfirmPassword(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        RegisterButton()
    }
}

@Composable
fun RegisterButton() {
    Button(
        onClick = { },
        enabled = true,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xff0097a7),
            disabledBackgroundColor = Color(0xFF80deea),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "REGISTRAR")
    }
}

@Composable
fun Password(modifier: Modifier) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = password ,
        onValueChange = { password = it },
        modifier = modifier,
        placeholder = { Text(text = "Contraseña") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = Color(0xff00bcd4),
        ),
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
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun ConfirmPassword(modifier: Modifier) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = password ,
        onValueChange = { password = it },
        modifier = modifier,
        placeholder = { Text(text = "Contraseña") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = Color(0xff00bcd4),
        ),
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

    OutlinedTextField(
        value = email ,
        onValueChange = { email = it },
        modifier = modifier,
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = Color(0xff00bcd4),
        ),
        trailingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "user" ) }
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
