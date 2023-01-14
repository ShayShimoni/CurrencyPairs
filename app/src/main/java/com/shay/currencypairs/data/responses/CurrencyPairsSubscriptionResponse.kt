package com.shay.currencypairs.data.responses

data class CurrencyPairsSubscriptionResponse(
    val channelID: Int,
    val channelName: String,
    val event: String,
    val pair: String,
    val status: String,
    val subscription: Map<String,String>
)
