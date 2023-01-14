package com.shay.currencypairs.ui

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.shay.currencypairs.WebSocketService
import com.shay.currencypairs.data.entities.CurrencyPairType
import com.shay.currencypairs.data.responses.CurrencyPairsSubscriptionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CurrencyPairsViewModel : ViewModel() {

    private val _currencyPair: MutableStateFlow<List<*>> = MutableStateFlow(emptyList<List<*>>())
    val currencyPair: StateFlow<List<*>> by lazy { _currencyPair }

    private var count = 0
    private val channels = mutableListOf<CurrencyPairsSubscriptionResponse>()

    private val service: WebSocketService =
        WebSocketService(object : WebSocketService.CurrencyPairCallBack {
            override fun onCurrencyPairUpdate(response: String) {

                if (count == 0) {
                    count++
                } else if (count <= CurrencyPairType.values().size) {
                    channels.add(Gson().fromJson(response, CurrencyPairsSubscriptionResponse::class.java))
                    count++
                } else {
                    if (!response.contains("heartbeat")) {
                        _currencyPair.update {
                            Gson().fromJson(response, List::class.java)
                        }
                    }
                }

            }
        })

    fun subscribe() {
        service.subscribeToCurrencyPair()
    }

}