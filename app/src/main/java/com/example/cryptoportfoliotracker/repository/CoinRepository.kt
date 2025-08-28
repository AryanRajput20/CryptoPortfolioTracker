package com.example.cryptoportfoliotracker.data

import com.example.cryptoportfoliotracker.model.Coin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoinRepository {

    private val api: CoinGeckoApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CoinGeckoApi::class.java)
    }

    suspend fun getCoins(): List<Coin> = api.getCoins()

    // Fetch coins by comma-separated IDs
    suspend fun getCoinsByIds(ids: String): List<Coin> {
        return api.getCoins(coinIds = ids)
    }
}
