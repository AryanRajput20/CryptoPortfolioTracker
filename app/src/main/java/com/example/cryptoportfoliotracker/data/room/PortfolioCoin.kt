package com.example.cryptoportfoliotracker.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio_coin")
data class PortfolioCoin(
    @PrimaryKey val coinId: String,
    val name: String,
    val symbol: String,
    val buyPrice: Double,
    val quantity: Double,
    val currentPrice: Double
)




