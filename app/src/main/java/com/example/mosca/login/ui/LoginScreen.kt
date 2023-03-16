package com.example.mosca.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mosca.R

@Preview(showBackground = true)
@Composable
fun LoginScreen(){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrandLogo()
        LoginForm(Modifier.padding(vertical = 16.dp))
        LoginDivider()
        Text(
            text = "Registrate",
            Modifier.padding(8.dp),
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
            text = "OR",
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
fun LoginForm(modifier: Modifier) {
    Column (modifier = modifier) {
        Email(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(8.dp))
        Password(Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton()
    }
}

@Composable
fun LoginButton() {
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
        Text(text = "INGRESAR")
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
        )
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
