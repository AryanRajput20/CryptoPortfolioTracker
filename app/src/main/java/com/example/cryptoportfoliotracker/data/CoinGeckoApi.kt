package com.example.cryptoportfoliotracker.data

import com.example.cryptoportfoliotracker.model.Coin
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") currency: String = "usd",
        @Query("ids") coinIds: String? = null
    ): List<Coin>
}



