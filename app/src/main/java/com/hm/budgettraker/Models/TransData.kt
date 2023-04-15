package com.hm.budgettraker.Models

data class TransData(
    var id: Int = 0,
    var amount:Int = 0,
    var category:String,
    var note:String,
    var isExpense:Boolean
)
