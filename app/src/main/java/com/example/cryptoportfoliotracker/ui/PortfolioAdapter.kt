package com.example.cryptoportfoliotracker.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoportfoliotracker.R
import com.example.cryptoportfoliotracker.data.room.PortfolioCoin

class PortfolioAdapter(
    private val onCoinClick: (PortfolioCoin) -> Unit,
    private val onCoinLongClick: (PortfolioCoin) -> Unit
) : RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    private var coins: List<PortfolioCoin> = emptyList()

    class PortfolioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.coinName)
        val quantity: TextView = itemView.findViewById(R.id.coinQuantity)
        val buyPrice: TextView = itemView.findViewById(R.id.coinBuyPrice)
        val currentPrice: TextView = itemView.findViewById(R.id.coinCurrentPrice)
        val profitLoss: TextView = itemView.findViewById(R.id.coinProfitLoss)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_portfolio, parent, false)
        return PortfolioViewHolder(view)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val coin = coins[position]

        holder.name.text = "${coin.name} (${coin.symbol})"
        holder.quantity.text = "Qty: ${coin.quantity}"
        holder.buyPrice.text = "Buy: $${coin.buyPrice}"
        holder.currentPrice.text = "Now: $${coin.currentPrice}"

        val profitLossValue = (coin.currentPrice - coin.buyPrice) * coin.quantity
        holder.profitLoss.text = "P/L: $${String.format("%.2f", profitLossValue)}"

        if (profitLossValue >= 0) {
            holder.profitLoss.setTextColor(Color.GREEN)
        } else {
            holder.profitLoss.setTextColor(Color.RED)
        }

        holder.itemView.setOnClickListener { onCoinClick(coin) }
        holder.itemView.setOnLongClickListener {
            onCoinLongClick(coin)
            true
        }
    }

    override fun getItemCount(): Int = coins.size

    fun submitList(newCoins: List<PortfolioCoin>) {
        coins = newCoins
        notifyDataSetChanged()
    }
}





