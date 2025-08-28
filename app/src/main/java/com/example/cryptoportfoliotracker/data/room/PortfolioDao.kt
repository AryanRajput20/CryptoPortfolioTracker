package com.example.cryptoportfoliotracker.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PortfolioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(coin: PortfolioCoin)

    @Update
    suspend fun updateCoin(coin: PortfolioCoin)

    @Delete
    suspend fun deleteCoin(coin: PortfolioCoin)

    @Query("SELECT * FROM portfolio_coin")
    fun getAllCoins(): LiveData<List<PortfolioCoin>>
}



