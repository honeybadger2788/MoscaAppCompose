package com.example.mosca.ui.screen.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mosca.core.Event
import com.example.mosca.domain.GetCurrentUserUseCase
import com.example.mosca.domain.LogoutUseCase
import com.example.mosca.ui.screen.home.model.ExpenseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
):ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _budget = MutableLiveData<Double>()
    val budget: LiveData<Double> = _budget

    private val _expensesList = mutableStateListOf<ExpenseModel>()
    val expensesList: List<ExpenseModel> = _expensesList

    private val _navigateToLogin = MutableLiveData<Event<Boolean>>()
    val navigateToLogin: LiveData<Event<Boolean>> = _navigateToLogin

    init {
        viewModelScope.launch {
            getCurrentUserUseCase()
                .collect{userLoggedIn ->
                    if(!userLoggedIn)
                        _navigateToLogin.value = Event(true)
                }
        }
    }

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