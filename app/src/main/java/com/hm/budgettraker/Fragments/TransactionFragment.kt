package com.hm.budgettraker.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.hm.budgettraker.Adapters.TransactionAdapter
import com.hm.budgettraker.Database.DatabaseHelper
import com.hm.budgettraker.R
import com.hm.budgettraker.databinding.FragmentTransactionBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TransactionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentTransactionBinding

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

        var adapter = TransactionAdapter()
        binding.rcvTransactionList.adapter = adapter

        adapter.addData(dbHelper.getTransaction())



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