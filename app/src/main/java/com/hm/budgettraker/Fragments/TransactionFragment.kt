package com.hm.budgettraker.Fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hm.budgettraker.AdapterClicks.TransListClick
import com.hm.budgettraker.Adapters.TransactionAdapter
import com.hm.budgettraker.Database.DatabaseHelper
import com.hm.budgettraker.Models.TransData
import com.hm.budgettraker.databinding.FragmentTransactionBinding
import com.hm.budgettraker.databinding.UpdateTransDialogBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TransactionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentTransactionBinding
    var adapter = TransactionAdapter()

    lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTransactionBinding.inflate(layoutInflater)

        initView()

        return binding.root
    }

    private fun initView() {

        dbHelper = DatabaseHelper(context)
        binding.rcvTransactionList.layoutManager = LinearLayoutManager(context)

        var click = object : TransListClick{
            override fun update(transData: TransData) {
                updateData(transData)
            }

            override fun delete(transData: TransData) {
                deleteData(transData.id)
            }

        }


        binding.rcvTransactionList.adapter = adapter
        adapter.updateData(dbHelper.getTransaction())

        Toast.makeText(context, ""+dbHelper.getTransaction().size, Toast.LENGTH_SHORT).show()

    }

    private fun deleteData(id: Int) {
        if (dbHelper.deleteTransaction(id)) {
            adapter.updateData(dbHelper.getTransaction())
        }
    }

    @SuppressLint("UseRequireInsteadOfGet", "ResourceAsColor")
    private fun updateData(transData: TransData) {

        var dialog = Dialog(context!!)
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))
        var bind = UpdateTransDialogBinding.inflate(LayoutInflater.from(context),null,false)
        dialog.setContentView(bind.root)

        bind.edtAmount.setText(transData.amount.toString())
        bind.edtCategory.setText(transData.category.toString())
        bind.edtNote.setText(transData.note.toString())

        bind.cardUpdate.setOnClickListener {
            var amt = bind.edtAmount.text.toString().toInt()
            var category = bind.edtCategory.text.toString()
            var note = bind.edtNote.text.toString()

            var data = TransData(transData.id,amt,category, note, transData.isExpense)

            if (dbHelper.updateIncomeExpense(data)) {
                Toast.makeText(context, "Data Update Success", Toast.LENGTH_SHORT).show()
                adapter.updateData(dbHelper.getTransaction())
            } else {
                Toast.makeText(context, "Data Update Failed", Toast.LENGTH_SHORT).show()
            }

        }

        dialog.show()

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransactionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}