package com.shay.currencypairs.data.entities

enum class CurrencyPairType(val value: String) {
    XBT_EUR("XBT/EUR"),
    XBT_USD("XBT/USD"),
    XBT_GBP("XBT/GBP"),
    ETH_EUR("ETH/EUR"),
    ETH_USD("ETH/USD");

    companion object{

        fun toEnum(value: String): CurrencyPairType? {
            return values().find {
                it.value == value
            }
        }
    }
}