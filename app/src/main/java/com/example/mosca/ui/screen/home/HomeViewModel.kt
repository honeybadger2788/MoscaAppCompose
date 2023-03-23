package com.example.mosca.ui.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mosca.domain.LogoutUseCase
import com.example.mosca.ui.screen.home.model.ExpenseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase
):ViewModel() {
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

    fun onLogout() {
        logoutUseCase()
    }
}