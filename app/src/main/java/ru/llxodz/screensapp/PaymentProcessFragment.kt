package ru.llxodz.screensapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ru.llxodz.screensapp.databinding.FragmentPaymentProcessBinding

class PaymentProcessFragment : Fragment() {

    private var _binding: FragmentPaymentProcessBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerAdapter: PaymentMethodsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentProcessBinding.inflate(inflater, container, false)
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
        recyclerAdapter = PaymentMethodsListAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PaymentProcessFragment()
    }
}
