package ru.llxodz.screensapp.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item_payment_method.view.*
import ru.llxodz.screensapp.R
import ru.llxodz.screensapp.models.PaymentModel

class PaymentMethodsListAdapter(
    private val context: Context,
    private val listener: OnItemClickListener,
    private var rowIndex: Int = 0
) :
    RecyclerView.Adapter<PaymentMethodsListAdapter.PaymentMethodsViewHolder>() {

    private var paymentMethodsList = java.util.ArrayList<PaymentModel>()

    fun setUpdatedData(items: java.util.ArrayList<PaymentModel>) {
        this.paymentMethodsList = items
        notifyDataSetChanged()
    }

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PaymentMethodsViewHolder, position: Int) {
        holder.itemView.rv_tv_paid_time.text = paymentMethodsList[position].subscriptionTime
        holder.itemView.rv_tv_cost_paid_time.text =
            "${paymentMethodsList[position].subscriptionPrice} руб."
        holder.itemView.rv_tv_description_payment_method.text =
            paymentMethodsList[position].subscriptionDescription

        holder.itemView.setOnClickListener {
            rowIndex = position
            notifyDataSetChanged()
        }

        holder.bind()
    }

    override fun getItemCount(): Int = paymentMethodsList.size

    inner class PaymentMethodsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bind() {
            if (rowIndex == adapterPosition) {
                itemView.setBackgroundResource(R.drawable.bg_layout_methods_payment_selected)
                itemView.rv_tv_description_payment_method.text =
                    context.getString(R.string.string_description_selected_item)
                itemView.ic_selected_payment_method.visibility = View.VISIBLE
                itemView.rv_tv_description_payment_method.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.black
                    )
                )
            } else {
                itemView.setBackgroundResource(R.drawable.bg_layout_methods_payment_unselected)
                itemView.ic_selected_payment_method.visibility = View.GONE
                itemView.rv_tv_description_payment_method.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.primaryGray
                    )
                )
            }
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
