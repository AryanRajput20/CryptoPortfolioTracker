package com.example.cryptoportfoliotracker.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoportfoliotracker.data.room.AppDatabase
import com.example.cryptoportfoliotracker.data.room.PortfolioCoin
import com.example.cryptoportfoliotracker.databinding.ActivityPortfolioBinding
import com.example.cryptoportfoliotracker.repository.PortfolioRepository
import com.example.cryptoportfoliotracker.viewmodel.PortfolioViewModel
import com.example.cryptoportfoliotracker.viewmodel.PortfolioViewModelFactory

class PortfolioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPortfolioBinding
    private lateinit var vm: PortfolioViewModel
    private lateinit var adapter: PortfolioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPortfolioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = PortfolioRepository(AppDatabase.getDatabase(this).portfolioDao())
        vm = ViewModelProvider(this, PortfolioViewModelFactory(repository))
            .get(PortfolioViewModel::class.java)

        adapter = PortfolioAdapter(
            onCoinClick = { coin ->
                val intent = Intent(this, CoinDetailActivity::class.java).apply {
                    putExtra("coinId", coin.coinId)
                    putExtra("coinName", coin.name)
                    putExtra("coinPrice", coin.currentPrice)
                    putExtra("buyPrice", coin.buyPrice)
                    putExtra("quantity", coin.quantity)
                    putExtra("editMode", true)
                }
                startActivity(intent)
            },
            onCoinLongClick = { coin ->
                vm.delete(coin)
            }
        )

        binding.rvPortfolio.layoutManager = LinearLayoutManager(this)
        binding.rvPortfolio.adapter = adapter

        vm.portfolio.observe(this) { list ->
            adapter.submitList(list)
        }

        // Add new coin button
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CoinDetailActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        fun start(ctx: Context) = ctx.startActivity(Intent(ctx, PortfolioActivity::class.java))
    }
}





