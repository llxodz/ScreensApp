package ru.llxodz.screensapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.llxodz.screensapp.databinding.FragmentPaymentProcessBinding

class PaymentProcessFragment : Fragment(), PaymentMethodsListAdapter.OnItemClickListener {

    private var _binding: FragmentPaymentProcessBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: PaymentMethodsListAdapter
    private val exampleList = arrayListOf<PaymentModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentProcessBinding.inflate(inflater, container, false)
        exampleList.add(PaymentModel("1 месяц", 599, "Ежемесячная подписка"))
        exampleList.add(PaymentModel("2 месяц", 599, "Ежемесячная подписка"))
        exampleList.add(PaymentModel("3 месяца", 59922, "Ежемесячная подписка"))
//        exampleList.add(PaymentModel("4 месяца", 599, "Ежемесячная подписка"))
//        exampleList.add(PaymentModel("12 месяцев", 1012, "Ежемесячная подписка"))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showPaymentMethodsAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPaymentMethodsAdapter() {
        val recyclerView = binding.rvMethodsPayment
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = PaymentMethodsListAdapter(requireActivity(), exampleList, this)
        recyclerView.adapter = recyclerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PaymentProcessFragment()
    }

    override fun onItemClick(position: Int) {
        recyclerAdapter.notifyItemChanged(position)
    }
}
