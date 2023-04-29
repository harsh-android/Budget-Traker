package com.hm.budgettraker.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.hm.budgettraker.Database.DatabaseHelper
import com.hm.budgettraker.Models.TransData
import com.hm.budgettraker.databinding.FragmentAddBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    var isExpense = 0
    lateinit var databaseHelper: DatabaseHelper
    lateinit var binding: FragmentAddBinding

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
        binding = FragmentAddBinding.inflate(layoutInflater)

        initView()

        return binding.root
    }

    private fun initView() {
        databaseHelper = DatabaseHelper(context)

        binding.switcher.setOnCheckedChangedListener { checked ->
            if (checked) {
                isExpense = 1
                binding.txtTitle.text = "Add Income"
                binding.btnText.text = "Add Income"
            } else {
                isExpense = 0
                binding.txtTitle.text = "Add Expense"
                binding.btnText.text = "Add Expense"
            }
        }

        binding.cardAdd.setOnClickListener {
            var amt = binding.edtAmount.text.toString().toInt()
            var category = binding.edtCategory.text.toString()
            var note = binding.edtNote.text.toString()

            var data = TransData(0,amt,category, note, isExpense)

            if (databaseHelper.addIncomeExpense(data)) {
                Toast.makeText(context, "Data Inserted Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Data Insert Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}