package com.example.mosca.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.mosca.model.Routes
import com.example.mosca.ui.composable.CustomTextFieldOutlined
import com.example.mosca.ui.composable.DefaultButton
import com.example.mosca.ui.screen.home.model.ExpenseModel


@Composable
fun HomeScreen(homeViewModel: HomeViewModel, navigationController: NavHostController) {
    val showDialog: Boolean by homeViewModel.showDialog.observeAsState(false)
    val budget: Double by homeViewModel.budget.observeAsState(0.00)
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNav {
            homeViewModel.onLogout()
            navigationController.navigate(Routes.Login.route)
        } },
        floatingActionButton = { FabDialog(){ homeViewModel.onShowDialogClick() } },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        AddExpensesDialog(showDialog, homeViewModel, budget)
        Body(Modifier.padding(it), budget, homeViewModel)
    }
}

@Composable
fun BottomNav(logout: () -> Unit) {
    BottomNavigation(
        backgroundColor = Color(0xff0097a7),
        contentColor = Color.White
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = {  },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )},
            label = { Text(text = "Home")}
        )
        BottomNavigationItem(
            selected = false,
            onClick = { logout() },
            icon = {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout"
                )},
            label = { Text(text = "Logout")}
        )
    }
}

@Composable
fun Body(modifier: Modifier, budget: Double, homeViewModel: HomeViewModel) {
    Column(modifier = modifier) {
        Budget(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp), budget)
        ExpensesList(Modifier.padding(16.dp), homeViewModel)
    }
}

@Composable
fun ExpensesList(modifier: Modifier, homeViewModel: HomeViewModel) {
    val expenses: List<ExpenseModel> = homeViewModel.expensesList

    Column(modifier = modifier) {
        Text(text = "Movimientos:", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn{
            items(expenses){
                ItemExpense(it)
            }
        }
    }
}

@Composable
fun ItemExpense(expense: ExpenseModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (expense.amount > 0.00) {
            Icon(
                imageVector = Icons.Outlined.ArrowUpward,
                contentDescription = "income",
                tint = Color(0xff388E3C)
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.ArrowDownward,
                contentDescription = "outcome",
                tint = Color(0xffD32F2F)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(text = "${expense.detail} $${expense.amount}")
    }
}

@Composable
fun Budget(modifier: Modifier, budget: Double) {
    Card(modifier = modifier, elevation = 8.dp, backgroundColor = Color(0xff0097a7)) {
        Text(
            text = "Saldo: $${budget}",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White
        )
    }
}

@Composable
fun FabDialog(showDialog: () -> Unit) {
    FloatingActionButton(onClick = { showDialog() },
        modifier = Modifier.padding(16.dp),
        backgroundColor = Color(0xffffd740),
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "")
    }
}

@Composable
fun AddExpensesDialog(show: Boolean, homeViewModel: HomeViewModel, budget: Double) {
    var detail by rememberSaveable {
        mutableStateOf("")
    }

    var amount by rememberSaveable {
        mutableStateOf("")
    }

    if(show){
        Dialog(onDismissRequest = { homeViewModel.onDialogClose() }) {
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
                    CustomTextFieldOutlined(
                        label = "Detalle",
                        textValue = detail,
                        onTextChanged = { detail = it },
                        modifier = Modifier.weight(2f)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    CustomTextFieldOutlined(
                        label = "$",
                        textValue = amount,
                        onTextChanged = { amount = it },
                        modifier = Modifier.weight(1f),
                        keyboardType = KeyboardType.Number
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                DefaultButton(text = "AGREGAR", onClick = {
                    homeViewModel.onExpenseCreated(
                        ExpenseModel(
                            detail = detail,
                            amount = amount.toDouble()
                        ),
                        budget
                    )
                    amount = ""
                    detail = ""
                })
            }
        }
    }
}
