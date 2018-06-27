package com.example.mayank.payumoneydemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.app.Application
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.core.PayUmoneyConfig
import android.os.AsyncTask.execute
import com.payumoney.core.PayUmoneyConstants
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import org.json.JSONException
import org.json.JSONObject
import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.widget.EditText
import com.example.mayank.payumoneydemo.Constants.MERCHANT_ID
import com.example.mayank.payumoneydemo.Constants.MERCHANT_KEY
import com.example.mayank.payumoneydemo.Constants.URL
import com.example.mayank.payumoneydemo.Constants.furl
import com.example.mayank.payumoneydemo.Constants.surl
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import android.R.attr.data
import android.app.Activity
import android.content.DialogInterface
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.util.Log
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.ResultModel


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private var amount : Double? = null
    private lateinit var firstName : String
    private lateinit var mobileNumber : String
    private lateinit var email : String
    private lateinit var productName : String
    private lateinit var payUMoney: PayUMoney

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        payUMoney = PayUMoney(this)

    }

    fun payNow(view : View){
        amount = findViewById<EditText>(R.id.amount).text.toString().toDouble()
        firstName = findViewById<EditText>(R.id.first_name).text.toString()
        mobileNumber = findViewById<EditText>(R.id.mobile_number).text.toString()
        email = findViewById<EditText>(R.id.email).text.toString()
        productName = findViewById<EditText>(R.id.product_name).text.toString()
        payUMoney.launchPayUMoney(amount!!,firstName,mobileNumber,email, productName)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result Code is -1 send from Payumoney activity
        Log.d("MainActivity", "request code $requestCode resultCode $resultCode")

        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data !=
                null) {
//            var transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            var transactionResponse = data.getParcelableExtra<TransactionResponse>(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)

            var resultModel = data.getParcelableExtra<ResultModel>(PayUmoneyFlowManager.ARG_RESULT);

            // Check which object is non-null
            if (transactionResponse?.getPayuResponse() != null) {
                if (transactionResponse.transactionStatus == TransactionResponse.TransactionStatus.SUCCESSFUL) {
                    //Success Transaction
                    Log.d(TAG, "Successfull transaction")

                } else {
                    //Failure Transaction
                    Log.d(TAG, "Failed transaction")
                }

                // Response from Payumoney
                val payuResponse = transactionResponse.getPayuResponse();
                Log.d(TAG, "Pay u Response - $payuResponse")

                // Response from SURl and FURL
                val merchantResponse = transactionResponse.transactionDetails

                Log.d(TAG, "Merchant Response - $merchantResponse")

               AlertDialog.Builder(this)
                        .setCancelable(false)
                        .setMessage("Payu's Data : $payuResponse\n\n\n Merchant's Data: $merchantResponse")
                        .setPositiveButton(android.R.string.ok,DialogInterface.OnClickListener { dialog, whichButton ->
                            dialog.dismiss()
                        })

            } else if (resultModel?.error != null) {
                Log.d(TAG, "Error response : " + resultModel.error.transactionResponse);
            } else {
                Log.d(TAG, "Both objects are null!");
            }
        }
    }

}
