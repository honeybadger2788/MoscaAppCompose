package com.example.mosca.home.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mosca.home.ui.model.ExpenseModel

class HomeViewModel:ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _expensesList = mutableStateListOf<ExpenseModel>()
    val expensesList: List<ExpenseModel> = _expensesList

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onExpenseCreated(expense: ExpenseModel) {
        _showDialog.value = false
        _expensesList.add(expense)
    }
}