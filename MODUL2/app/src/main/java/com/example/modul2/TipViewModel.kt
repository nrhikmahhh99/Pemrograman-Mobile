package com.example.modul2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TipViewModel : ViewModel() {
    var tipResult: String = ""

    private val _tipAmount = MutableLiveData<Double>()
    val tipAmount: LiveData<Double> get() = _tipAmount

    fun calculateTip(totalAmount: Double, tipPercentage: Double) {
        val tip = totalAmount * (tipPercentage / 100)
        _tipAmount.value = tip
    }

    fun resetTip() {
        _tipAmount.value = 0.0
    }
}
