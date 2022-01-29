package ru.llxodz.screensapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PaymentMethodsListAdapter() :
    RecyclerView.Adapter<PaymentMethodsListAdapter.PaymentMethodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodsViewHolder {
        return PaymentMethodsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.row_item_payment_method,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: PaymentMethodsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 5

    inner class PaymentMethodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
