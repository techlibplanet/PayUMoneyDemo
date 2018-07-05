package com.example.mayank.myplaygame.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Mayank on 21/02/2018.
 */
class Question{
    @SerializedName("ques_id")
    var quesCode: String? = null

    @SerializedName("question")
    var question: String? = null

    @SerializedName("option_a")
    var optionA: String? = null

    @SerializedName("option_b")
    var optionB: String? = null

    @SerializedName("option_c")
    var optionC: String? = null

    @SerializedName("option_d")
    var optionD: String? = null

    @SerializedName("option_e")
    var optionE: String? = null

    @SerializedName("answer")
    var answer: String? = null
}