package com.example.mosca.home.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mosca.R

@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    var show by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        Modifier
            .fillMaxSize()) {
        AddExpensesDialog(show, onDismiss = { show =  false })
        Column {
            TopBar()
            Budget(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
            ExpensesList(Modifier.padding(16.dp))
        }
        FabDialog(Modifier.align(Alignment.BottomEnd)) { show = true }
    }
}

@Composable
fun TopBar() {
    TopAppBar(title = {},
        backgroundColor = Color(0xff0097a7),
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "logout",
                    tint = Color.White
                )
            }
        }
    )
}


@Composable
fun ExpensesList(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Movimientos:", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn{
            items(20){
                ItemExpense()
            }
        }
    }
}

@Composable
fun ItemExpense() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Outlined.ArrowUpward, contentDescription = "")
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "Gasto $0.00")
    }
}

@Composable
fun Budget(modifier: Modifier) {
    Card(modifier = modifier, elevation = 8.dp) {
        Text(
            text = "Saldo: $0.00",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun FabDialog(modifier: Modifier, showDialog: () -> Unit) {
    FloatingActionButton(onClick = { showDialog() },
        modifier = modifier.padding(16.dp),
        backgroundColor = Color(0xffffd740)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddExpensesDialog(show: Boolean, onDismiss: () -> Unit) {
    var detail by rememberSaveable {
        mutableStateOf("")
    }

    var amount by rememberSaveable {
        mutableStateOf("")
    }

    if(show){
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)) {
                Text(
                    text = "Agregar movimiento",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row {
                    TextField(
                        modifier = Modifier.weight(2f),
                        placeholder = { Text(text = "Detalle") },
                        value = detail,
                        onValueChange = { detail = it },
                        singleLine = true,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    TextField(
                        modifier = Modifier.weight(1f),
                        placeholder = { Text(text = "$") },
                        value = amount,
                        onValueChange = { amount = it },
                        singleLine = true,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Button(
                    onClick = { Log.i("Noe", "Info enviada ${detail} ${amount}")  },
                    Modifier.fillMaxWidth()
                ) {
                    Text(text = "Agregar")
                }
            }
        }
    }
}
