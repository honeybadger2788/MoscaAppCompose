package com.example.mosca.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        Column {
            Budget(
                Modifier
                    .fillMaxWidth())
            ExpensesList(Modifier.padding(8.dp))
        }
        FabDialog(Modifier.align(Alignment.BottomEnd))
    }
}


@Composable
fun ExpensesList(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Expenses:", fontSize = 18.sp)
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
            .padding(top = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = Icons.Outlined.ArrowUpward, contentDescription = "")
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Expense $0.00")
    }
}

@Composable
fun Budget(modifier: Modifier) {
    Card(modifier = modifier, elevation = 8.dp) {
        Text(text = "Budget: $0.00", fontSize = 48.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun FabDialog(modifier: Modifier) {
    FloatingActionButton(onClick = { },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddExpensesDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: () -> Unit) {
    TODO("Not yet implemented")
}
