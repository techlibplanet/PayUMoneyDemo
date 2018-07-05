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

class TransferPoints : AppCompatActivity(), View.OnClickListener {

    private val TAG = TransferPoints::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_points)

        findViewById<Button>(R.id.transfer_points_button).setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val amount = findViewById<EditText>(R.id.amount_edit_text).text.toString().trim()
        val mobileNumber = findViewById<EditText>(R.id.mobile_number_edit_text).text.toString().trim()
        val transferTo = findViewById<EditText>(R.id.transfer_to_edit_text).text.toString().trim()

        val apiClient = ApiClient()
        var retrofit = apiClient.getService<Itransaction>()
        retrofit.transferPoints("Mayank", "Sharma", "PlayGameName", mobileNumber, transferTo, "", "mayank@gamil.com","Transfer Points",amount, "", "",
                "20/03/1990", "20/03/1990", "-", "-", "Debited","success").enqueue(object : Callback<Transactions>{
            override fun onFailure(call: Call<Transactions>?, t: Throwable?) {
                Log.d(TAG, "Error - $t")
            }

            override fun onResponse(call: Call<Transactions>?, response: Response<Transactions>?) {
                if (response?.isSuccessful!!){
                    Log.d(TAG, "Response - $response")
                    val responseBody = response.body()
                    Log.d(TAG, "MobileNumber - ${responseBody?.mobileNumber}")
                    Log.d(TAG, "Total Balance - ${responseBody?.balance}")

                    AlertDialog.Builder(this@TransferPoints).setTitle("Transfer Points")
                            .setMessage("Transfer points successfully!\n Balance : ${responseBody?.balance}")
                            .setNeutralButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                                dialog.dismiss()
                            }).show()
                }
            }

        })
    }
}
