package model

import model.Currency

data class Price(val currency: Currency, val wholePrice:Int, val meterPrice:Int)
