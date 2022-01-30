package ru.llxodz.screensapp.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.razir.progressbutton.bindProgressButton
import com.github.razir.progressbutton.showProgress
import ru.llxodz.screensapp.R
import ru.llxodz.screensapp.databinding.FragmentPaymentProcessBinding
import ru.llxodz.screensapp.ui.adapters.PaymentMethodsListAdapter
import ru.llxodz.screensapp.viewmodel.PaymentProcessViewModel

class PaymentProcessFragment : Fragment(), PaymentMethodsListAdapter.OnItemClickListener {

    private var _binding: FragmentPaymentProcessBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PaymentProcessViewModel
    private lateinit var recyclerAdapter: PaymentMethodsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentProcessBinding.inflate(inflater, container, false)

        bindProgressButton(binding.btnPayment)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PaymentProcessViewModel::class.java]

        showPaymentMethodsAdapter()
        initViewModel()

        binding.btnPayment.setOnClickListener {
            binding.btnPayment.showProgress {
                progressColor = Color.WHITE

            }
            binding.btnPayment.isClickable = false
            viewModel.clickButtonPurchase()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        recyclerAdapter.notifyItemChanged(position)
    }

    private fun showPaymentMethodsAdapter() {
        val recyclerView = binding.rvMethodsPayment
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = PaymentMethodsListAdapter(requireActivity(), this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        viewModel.getTextButton().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.btnPayment.text = it
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.string_error_response),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        viewModel.getPaymentMethodsList().observe(viewLifecycleOwner) {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.string_error_response),
                    Toast.LENGTH_LONG
                ).show()
                recyclerAdapter.setUpdatedData(arrayListOf())
                binding.btnPayment.isClickable = false
            }
        }
        viewModel.makeRequest()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PaymentProcessFragment()
    }
}
