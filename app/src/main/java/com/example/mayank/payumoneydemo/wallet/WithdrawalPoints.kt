package com.example.mayank.payumoneydemo.wallet

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.mayank.myplaygame.network.ApiClient
import com.example.mayank.payumoneydemo.R
import com.example.mayank.payumoneydemo.network.wallet.Itransaction
import com.example.mayank.payumoneydemo.network.wallet.Transactions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WithdrawalPoints : AppCompatActivity(), View.OnClickListener {


    private var TAG = WithdrawalPoints::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdrawal_points)

        findViewById<Button>(R.id.withdrawal_points_button).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.withdrawal_points_button ->{
                withdrawalPoints()
            }
        }
    }

    private fun withdrawalPoints() {
        val amount = findViewById<EditText>(R.id.amount_edit_text).text.toString().trim()
        val apiClient = ApiClient()
        var retrofit = apiClient.getService<Itransaction>()
        retrofit.withdrawalPayments("Mayank","Sharma","PlayGameName","9893192579","","","mayank84735@gmail.com",
                "Withdrawal Payments",amount,"ABCD123456","ABCD123456","20/03/1990","20/03/1990",
                "ABCD123456","123456","Debited","32895984735","SBIN0007726","process").enqueue(object : Callback<Transactions>{
            override fun onFailure(call: Call<Transactions>?, t: Throwable?) {
                Log.d(TAG, "Error - $t")
            }

            override fun onResponse(call: Call<Transactions>?, response: Response<Transactions>?) {
                if (response?.isSuccessful!!){
                    Log.d(TAG, "Response - $response")
                    val responseBody = response.body()
                    Log.d(TAG, "Mobile Number - ${responseBody?.mobileNumber}")
                    Log.d(TAG, "Balance - ${responseBody?.balance}")
                    AlertDialog.Builder(this@WithdrawalPoints).setTitle("Withdrawal Points")
                            .setMessage("Withdrawal points successfully!\n Balance : ${responseBody?.balance}")
                            .setNeutralButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                                dialog.dismiss()
                            }).show()
                }
            }

        })
    }
}
