package com.example.foodhub.data

import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {
    @GET("food")
    suspend fun getFood(): List<String>

//    @POST("/auth/signup")
//    suspend fun signUp(request: SignUpRequest): SignUpResponse)



}