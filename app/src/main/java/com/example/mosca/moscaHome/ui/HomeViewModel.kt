package com.example.mosca.moscaHome.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mosca.moscaHome.ui.model.ExpenseModel

class HomeViewModel:ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _budget = MutableLiveData<Double>()
    val budget: LiveData<Double> = _budget

    private val _expensesList = mutableStateListOf<ExpenseModel>()
    val expensesList: List<ExpenseModel> = _expensesList

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onExpenseCreated(expense: ExpenseModel, budget: Double) {
        _budget.value = budget + expense.amount
        _showDialog.value = false
        _expensesList.add(expense)
    }
}