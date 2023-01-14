package com.shay.currencypairs.listeners

import android.util.Log
import com.shay.currencypairs.WebSocketService
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class CurrencyPairsWebSocketListener(private val currencyPairCallBack: WebSocketService.CurrencyPairCallBack?) : WebSocketListener() {

    companion object{
        private const val TAG = "websocket"
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        Log.d(TAG, "onOpen: $response")
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d(TAG, "onClosed: $reason")

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d(TAG, "onFailure: ${t.message}")
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        currencyPairCallBack?.onCurrencyPairUpdate(text)
    }
}