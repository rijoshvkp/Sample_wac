package com.test.wacsample.service

import com.test.wacsample.model.ResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ProductsAPI {
    @GET("v3/17db81c4-f48e-408a-bf06-c289ee825e06") //extension
    fun getData() : Call<List<ResponseItem>>

}