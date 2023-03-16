package com.example.mosca.home.ui.model

data class ExpenseModel(var id: Long = System.currentTimeMillis() ,var detail: String, var amount: Double = 0.00)