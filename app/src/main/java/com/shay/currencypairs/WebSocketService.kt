package com.shay.currencypairs

import com.shay.currencypairs.data.entities.CurrencyPairType
import com.shay.currencypairs.listeners.CurrencyPairsWebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request

class WebSocketService(currencyPairCallBack: CurrencyPairCallBack?) {

    companion object{
        private const val BASE_URL = "wss://ws.kraken.com"
    }

    private val webSocket = OkHttpClient().newWebSocket(
        request = Request.Builder().url(BASE_URL).build(),
        listener = CurrencyPairsWebSocketListener(currencyPairCallBack)
    )

    fun subscribeToCurrencyPair() {
        val pairs = buildString {
            CurrencyPairType.values().forEachIndexed { i, currencyPair ->
                if (i + 1 == CurrencyPairType.values().size) {
                    append("\"").append(currencyPair.value).append("\"").append(System.lineSeparator())
                } else {
                    append("\"").append(currencyPair.value).append("\"").append(",")
                        .append(System.lineSeparator())
                }
            }
        }

        webSocket.send(
            """
                    {
                        "event": "subscribe",
                        "pair": [
                           $pairs
                        ],
                        "subscription": {
                          "name": "ticker"
                         }
                    }
                 """
        )
    }

    interface CurrencyPairCallBack {
        fun onCurrencyPairUpdate(response: String)
    }
}