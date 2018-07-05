package com.example.mayank.payumoneydemo.wallet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mayank.payumoneydemo.MainActivity
import com.example.mayank.payumoneydemo.R

class WalletActivity : AppCompatActivity(), View.OnClickListener {

    private val CLICKABLES = arrayOf(R.id.add_balance_button, R.id.withdrawal_button, R.id.transfer_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        for (id in CLICKABLES){
            findViewById<Button>(id).setOnClickListener(this)
        }
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.add_balance_button ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.withdrawal_button ->{
                val intent = Intent(this, WithdrawalPoints::class.java)
                startActivity(intent)
            }
            R.id.transfer_button ->{
                val intent = Intent(this, TransferPoints::class.java)
                startActivity(intent)
            }
        }
    }
}
