package com.hm.budgettraker.AdapterClicks

import com.hm.budgettraker.Models.TransData

interface TransListClick {

    fun update(transData: TransData)

    fun delete(transData: TransData)

}