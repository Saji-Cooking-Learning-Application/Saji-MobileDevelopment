package com.dicoding.sajiapps.response.predict

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("datas")
	val datas: Datas,

	@field:SerializedName("prediction")
	val prediction: Prediction,

	@field:SerializedName("status")
	val status: Status
)

data class ClassName(

	@field:SerializedName("nama_menu")
	val namaMenu: String,

	@field:SerializedName("id")
	val id: Int
)

data class Datas(

	@field:SerializedName("karbohidrat")
	val karbohidrat: Int,

	@field:SerializedName("nama_menu")
	val namaMenu: String,

	@field:SerializedName("dan_lain_lain")
	val danLainLain: String,

	@field:SerializedName("vitamin_A")
	val vitaminA: Int,

	@field:SerializedName("vitamin_C")
	val vitaminC: Int,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("serat")
	val serat: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("jumlah_kalori")
	val jumlahKalori: Int,

	@field:SerializedName("lemak")
	val lemak: Int
)

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("message")
	val message: String
)

data class Prediction(

	@field:SerializedName("confidence")
	val confidence: String,

	@field:SerializedName("class_name")
	val className: ClassName
)
