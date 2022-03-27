package com.kimdo.good.network

import com.kimdo.good.ApiKey
import com.kimdo.good.models.AnimalResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AnimalApi {
    @GET("getKey")
    fun getKey(): Call<ApiKey>

//    @POST("getAnimals")
//    fun getAnimals(@Body key: ApiKey): Call<AnimalResponse>

    @FormUrlEncoded
    @POST("getAnimals")
    fun getAnimals(@Field("key") key:String): Call<AnimalResponse>
}

object RetrofitBuilder {
    val baseURL = "https://us-central1-apis-4674e.cloudfunctions.net/"
    var api: AnimalApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(AnimalApi::class.java)
    }
}