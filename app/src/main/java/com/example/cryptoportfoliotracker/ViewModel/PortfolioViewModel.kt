package com.example.cryptoportfoliotracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoportfoliotracker.data.room.PortfolioCoin
import com.example.cryptoportfoliotracker.repository.PortfolioRepository
import kotlinx.coroutines.launch

class PortfolioViewModel(private val repository: PortfolioRepository) : ViewModel() {

    val portfolio: LiveData<List<PortfolioCoin>> = repository.getAllPortfolioCoins()

    fun add(coin: PortfolioCoin) {
        viewModelScope.launch {
            repository.insertCoin(coin)
        }
    }

    fun update(coin: PortfolioCoin) {
        viewModelScope.launch {
            repository.updateCoin(coin)
        }
    }

    fun delete(coin: PortfolioCoin) {
        viewModelScope.launch {
            repository.deleteCoin(coin)
        }
    }
}













