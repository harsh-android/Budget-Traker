package com.hm.budgettraker.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hm.budgettraker.AdapterClicks.TransListClick
import com.hm.budgettraker.Models.TransData
import com.hm.budgettraker.databinding.TransationItemBinding

class TransactionAdapter : Adapter<TransactionAdapter.TransactionHolder>() {

    var transactionList = ArrayList<TransData>()
    lateinit var click : TransListClick

    class TransactionHolder(itemView: TransationItemBinding) : ViewHolder(itemView.root) {
        var data = itemView.apply {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        return TransactionHolder(
            TransationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {

        holder.apply {
            data.apply {
                transactionList.get(position).apply {

                    txtTitle.text = category
                    txtAmount.text = amount.toString()
                    txtDescription.text = note

                }
                imgEdit.setOnClickListener {
                    click.update(transactionList.get(position))
                }

                imgDelete.setOnClickListener {
                    click.delete(transactionList.get(position))
                }

            }

        }

    }

    fun updateData(transaction: ArrayList<TransData>, click: TransListClick) {
        this.transactionList = transaction
        this.click = click
        notifyDataSetChanged()
    }

}