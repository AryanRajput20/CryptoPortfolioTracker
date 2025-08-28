package com.example.cryptoportfoliotracker.model

import com.google.gson.annotations.SerializedName

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val image: String,

    @SerializedName("current_price")
    val currentPrice: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double
)




