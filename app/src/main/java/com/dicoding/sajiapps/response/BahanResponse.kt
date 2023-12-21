package com.dicoding.sajiapps.response

import com.google.gson.annotations.SerializedName

data class BahanResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItemBahan?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemBahan(

	@field:SerializedName("foto")
	val foto: String? = null,

	@field:SerializedName("nama_bahan")
	val namaBahan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
