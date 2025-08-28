package com.example.cryptoportfoliotracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoportfoliotracker.data.CoinRepository
import com.example.cryptoportfoliotracker.model.Coin
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val repository = CoinRepository()

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    fun fetchCoins() {
        viewModelScope.launch {
            try {
                val coinList = repository.getCoins()
                _coins.postValue(coinList)
            } catch (e: Exception) {
                e.printStackTrace()
                _coins.postValue(emptyList())
            }
        }
    }
}

