package com.example.cryptoportfoliotracker.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoportfoliotracker.R
import com.example.cryptoportfoliotracker.viewmodel.CoinViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CoinAdapter
    private val viewModel: CoinViewModel by viewModels() // Correct usage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CoinAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.coins.observe(this) { coinList ->
            if (coinList.isNotEmpty()) {
                Log.d("MainActivity", "Fetched coins: ${coinList.size}")
                adapter.updateData(coinList)
            } else {
                Log.w("MainActivity", "No coins received from API")
            }
        }

        viewModel.fetchCoins()
    }
}







