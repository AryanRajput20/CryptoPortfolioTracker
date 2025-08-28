package com.example.cryptoportfoliotracker.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.cryptoportfoliotracker.R
import com.example.cryptoportfoliotracker.data.room.AppDatabase
import com.example.cryptoportfoliotracker.data.room.PortfolioCoin
import kotlinx.coroutines.launch

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var tvCoinName: TextView
    private lateinit var tvCoinPrice: TextView
    private lateinit var etQuantity: EditText
    private lateinit var etBuyPrice: EditText
    private lateinit var btnSave: Button

    private lateinit var appDatabase: AppDatabase
    private var editMode = false
    private var coinId: String? = null
    private var coinName: String? = null
    private var coinPrice: Double = 0.0
    private var buyPrice: Double = 0.0
    private var quantity: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        tvCoinName = findViewById(R.id.tvCoinName)
        tvCoinPrice = findViewById(R.id.tvCoinPrice)
        etQuantity = findViewById(R.id.etQuantity)
        etBuyPrice = findViewById(R.id.etBuyPrice)
        btnSave = findViewById(R.id.btnSave)

        appDatabase = AppDatabase.getDatabase(this)

        // Get intent data
        coinId = intent.getStringExtra("coinId")
        coinName = intent.getStringExtra("coinName")
        coinPrice = intent.getDoubleExtra("coinPrice", 0.0)
        buyPrice = intent.getDoubleExtra("buyPrice", 0.0)
        quantity = intent.getDoubleExtra("quantity", 0.0)
        editMode = intent.getBooleanExtra("editMode", false)

        tvCoinName.text = coinName ?: "New Coin"
        tvCoinPrice.text = "Current Price: $${coinPrice}"

        if (editMode) {
            etBuyPrice.setText(buyPrice.toString())
            etQuantity.setText(quantity.toString())
            btnSave.text = "Update Coin"
        }

        btnSave.setOnClickListener {
            val q = etQuantity.text.toString().toDoubleOrNull()
            val bp = etBuyPrice.text.toString().toDoubleOrNull()

            if (q == null || bp == null) {
                Toast.makeText(this, "Enter valid values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val portfolioCoin = PortfolioCoin(
                coinId = coinId ?: System.currentTimeMillis().toString(),
                name = coinName ?: "Unknown",
                symbol = coinName?.take(3)?.uppercase() ?: "UNK",
                buyPrice = bp,
                quantity = q,
                currentPrice = coinPrice
            )

            lifecycleScope.launch {
                if (editMode) {
                    appDatabase.portfolioDao().updateCoin(portfolioCoin)
                    Toast.makeText(this@CoinDetailActivity, "Coin Updated", Toast.LENGTH_SHORT).show()
                } else {
                    appDatabase.portfolioDao().insertCoin(portfolioCoin)
                    Toast.makeText(this@CoinDetailActivity, "Coin Added", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }
    }
}
