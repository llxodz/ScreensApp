package ru.llxodz.screensapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.llxodz.screensapp.models.PaymentModel

class PaymentProcessViewModel: ViewModel() {

    private val textButton: MutableLiveData<String> = MutableLiveData()
    private val paymentMethodsList: MutableLiveData<ArrayList<PaymentModel>> = MutableLiveData()

    fun getTextButton(): MutableLiveData<String> {
        return textButton
    }

    fun getPaymentMethodsList(): MutableLiveData<ArrayList<PaymentModel>> {
        return paymentMethodsList
    }

    fun clickButtonPurchase() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            textButton.postValue("Успешно!")
        }
    }

    fun makeRequest() {
        val items = arrayListOf<PaymentModel>()
        items.add(PaymentModel("6 месяцев", 1990, "Полугодовая подписка"))
        items.add(PaymentModel("1 месяц", 499, "Ежемесячная подписка"))
        items.add(PaymentModel("Навсегда", 4990, "Один платеж"))

        paymentMethodsList.postValue(items)
    }

}