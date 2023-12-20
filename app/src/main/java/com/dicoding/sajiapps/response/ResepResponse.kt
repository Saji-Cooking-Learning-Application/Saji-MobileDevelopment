package com.dicoding.sajiapps.response

import com.google.gson.annotations.SerializedName

data class ResepResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("nama_menu")
	val namaMenu: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("id")
	val id: Int
)
