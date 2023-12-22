package com.dicoding.sajiapps.retrofit

import com.dicoding.sajiapps.response.BahanResponse
import com.dicoding.sajiapps.response.Data
import com.dicoding.sajiapps.response.DetailResepResponse
import com.dicoding.sajiapps.response.LoginResponse
import com.dicoding.sajiapps.response.RegisterResponse
import com.dicoding.sajiapps.response.ResepResponse
import com.dicoding.sajiapps.response.predict.PredictResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

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
        @Path("id") id:Int
    ): Response<Data>

    @GET("bahan")
    suspend fun getBahan(
        @Header("Authorization") token: String
    ): BahanResponse
}

interface ApiServicePredict{
    @Multipart
    @POST("predict/makanan")
    suspend fun postPredict(
        @Part file: MultipartBody.Part
    ): PredictResponse

}