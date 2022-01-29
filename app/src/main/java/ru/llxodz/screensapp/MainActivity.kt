package ru.llxodz.screensapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.llxodz.screensapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showPaymentProcessFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showPaymentProcessFragment() {
        val paymentProcessFragment = PaymentProcessFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(android.R.id.content, paymentProcessFragment)
        fragmentTransaction.commit()
    }
}