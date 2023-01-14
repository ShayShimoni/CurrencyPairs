package com.shay.currencypairs.data.entities

enum class CurrencyPairType(val value: String) {
    XBT_EUR("XBT/EUR"),
    XBT_USD("XBT/USD"),
    XBT_GBP("XBT/GBP"),
    ETH_EUR("ETH/EUR"),
    ETH_USD("ETH/USD"),
    ETH_GBP("ETH/GBP"),
    ATLAS_USD("ATLAS/USD"),
    ATOM_AUD("ATOM/AUD"),
    BADGER_EUR("BADGER/EUR"),
    BAL_EUR("BAL/EUR"),
    BNC_USD("BNC/USD"),
    CRV_ETH("CRV/ETH"),
    EUR_GBP("EUR/GBP");

    companion object{

        fun toEnum(value: String): CurrencyPairType? {
            return values().find {
                it.value == value
            }
        }
    }
}