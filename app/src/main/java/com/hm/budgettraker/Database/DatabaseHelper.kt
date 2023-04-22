package com.hm.budgettraker.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.hm.budgettraker.Models.TransData
import com.hm.budgettraker.Utils.Utils.Companion.TABLE_NAME

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, "Budget.db", null, 1) {

    var context = context

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE $TABLE_NAME(id INTEGER PRIMARY KEY AUTOINCREMENT, amount INTEGER, category TEXT, note TEXT, isexpense BOOLEAN)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun AddIncomeExpense(transData: TransData): Boolean {
        var value = ContentValues().apply {
            put("amount", transData.amount)
            put("category", transData.category)
            put("note", transData.note)
            put("isexpense", transData.isExpense)
        }
        var db = writableDatabase
        var iss: Int = db.insert("$TABLE_NAME", null, value).toInt()
        return iss != -1
    }

    fun getTransaction(): ArrayList<TransData> {

        var transList = ArrayList<TransData>()
        var db = readableDatabase
        var sql = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        do {

            var id = cursor.getInt(0)
            var amount = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpense = cursor.getInt(4)

            var transData = TransData(id, amount, category, note, isExpense)
            transList.add(transData)
        } while (cursor.moveToNext())
        return transList
    }

}