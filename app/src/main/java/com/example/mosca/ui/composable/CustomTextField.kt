package com.example.mosca.ui.composable

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CustomTextFieldOutlined(
    label: String,
    textValue: String,
    onTextChanged: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    error: Boolean = false
) {
    OutlinedTextField(
        value = textValue ,
        onValueChange = { onTextChanged(it) },
        modifier = modifier,
        label = { Text(text = label) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.DarkGray,
            backgroundColor = Color(0xFFFAFAFA),
            focusedBorderColor = Color(0xff00bcd4),
            unfocusedLabelColor = Color(0xFFB2B2B2),
            focusedLabelColor = Color(0xff00bcd4)
        ),
        trailingIcon = {
            if (error) {
                Icon(imageVector = Icons.Filled.Error, contentDescription = "alert", tint = Color.Red )
            }
            trailingIcon
        },
        visualTransformation = visualTransformation
    )
}