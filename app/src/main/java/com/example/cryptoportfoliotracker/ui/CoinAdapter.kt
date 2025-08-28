package com.example.cryptoportfoliotracker.ui

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptoportfoliotracker.R
import com.example.cryptoportfoliotracker.model.Coin
import com.example.cryptoportfoliotracker.ui.CoinDetailActivity

class CoinAdapter(private var coins: List<Coin>) :
    RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coinImage: ImageView = itemView.findViewById(R.id.coinImage)
        val coinName: TextView = itemView.findViewById(R.id.coinName)
        val coinPrice: TextView = itemView.findViewById(R.id.coinPrice)
        val coinChange: TextView = itemView.findViewById(R.id.coinChange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false)
        return CoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coins[position]

        // Set coin details
        holder.coinName.text = coin.name
        holder.coinPrice.text = "$${coin.currentPrice}"
        holder.coinChange.text = "${coin.priceChangePercentage24h}%"

        // Color based on price change
        holder.coinChange.setTextColor(
            if (coin.priceChangePercentage24h < 0) Color.RED else Color.GREEN
        )

        // Load image
        Glide.with(holder.itemView.context)
            .load(coin.image)
            .into(holder.coinImage)

        // Click listener to open CoinDetailActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CoinDetailActivity::class.java)
            intent.putExtra("coinId", coin.id)
            intent.putExtra("coinName", coin.name)
            intent.putExtra("coinPrice", coin.currentPrice)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = coins.size

    fun updateData(newCoins: List<Coin>) {
        coins = newCoins
        notifyDataSetChanged()
    }
}





