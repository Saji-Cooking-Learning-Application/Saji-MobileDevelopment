package com.dicoding.sajiapps.retrofit

import com.dicoding.sajiapps.response.DetailResepResponse
import com.dicoding.sajiapps.response.LoginResponse
import com.dicoding.sajiapps.response.RegisterResponse
import com.dicoding.sajiapps.response.ResepResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("hp") hp: String,
    ): RegisterResponse

    @GET("resep")
    suspend fun getResep(
        @Header("Authorization") token: String
    ): ResepResponse

    @GET("resep/{id}")
    suspend fun getDetailResep(
        @Header("Authorization") token: String,
        @Field("id") id: Int
    ): DetailResepResponse
}