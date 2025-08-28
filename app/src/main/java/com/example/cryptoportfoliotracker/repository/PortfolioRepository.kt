package com.example.cryptoportfoliotracker.repository

import androidx.lifecycle.LiveData
import com.example.cryptoportfoliotracker.data.room.PortfolioCoin
import com.example.cryptoportfoliotracker.data.room.PortfolioDao

class PortfolioRepository(private val dao: PortfolioDao) {

    fun getAllPortfolioCoins(): LiveData<List<PortfolioCoin>> = dao.getAllCoins()

    suspend fun insertCoin(coin: PortfolioCoin) = dao.insertCoin(coin)

    suspend fun updateCoin(coin: PortfolioCoin) = dao.updateCoin(coin)

    suspend fun deleteCoin(coin: PortfolioCoin) = dao.deleteCoin(coin)
}



