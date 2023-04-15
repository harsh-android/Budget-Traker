package com.hm.budgettraker.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hm.budgettraker.Models.TransData

class DatabaseHelper(context: Context? ) : SQLiteOpenHelper(context, "Budget.db", null, 1) {

    var context = context
    var TABLE_NAME = "trans"

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql = "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT, amount INTEGER, category TEXT, note TEXT, isexpense BOOLEAN)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun AddIncomeExpense(transData: TransData): Boolean {
        var value = ContentValues()
        value.put("amount",transData.amount)
        value.put("category",transData.category)
        value.put("note",transData.note)
        value.put("isexpense",transData.isExpense)
        var db = writableDatabase
        var iss:Int = db.insert("$TABLE_NAME",null,value).toInt()
        return iss != -1
    }

}