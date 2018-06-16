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


class MainActivity : AppCompatActivity() {

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

}
