package com.example.mayank.payumoneydemo.network.wallet

import com.google.gson.annotations.SerializedName

class Transactions {

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("lastName")
    var lastName: String? = null

    @SerializedName("playGameName")
    var playGameName: String? = null

    @SerializedName("mobileNumber")
    var mobileNumber: String? = null

    @SerializedName("transferTo")
    var transferTo: String? = null

    @SerializedName("receivedFrom")
    var receivedFrom: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("productInfo")
    var productInfo: String? = null

    @SerializedName("amount")
    var amount: String? = null

    @SerializedName("txnId")
    var txnId: String? = null

    @SerializedName("paymentId")
    var paymentId: String? = null

    @SerializedName("addedOn")
    var addedOn: String? = null

    @SerializedName("createdOn")
    var createdOn: String? = null

    @SerializedName("bankRefNumber")
    var bankRefNumber: String? = null

    @SerializedName("bankCode")
    var bankCode: String? = null

    @SerializedName("transactionType")
    var transactionType: String? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("balance")
    var balance: String? = null


}