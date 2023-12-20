package com.dicoding.sajiapps.response

import com.google.gson.annotations.SerializedName

data class DetailResepResponse(

	@field:SerializedName("nama_menu")
	val namaMenu: String,

	@field:SerializedName("assets")
	val assets: Assets,

	@field:SerializedName("nutrisi")
	val nutrisi: Nutrisi,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("resep")
	val resep: List<ResepItem>
)

data class Assets(

	@field:SerializedName("foto1")
	val foto1: String,

	@field:SerializedName("foto3")
	val foto3: String,

	@field:SerializedName("foto2")
	val foto2: String
)

data class Nutrisi(

	@field:SerializedName("karbohidrat")
	val karbohidrat: Int,

	@field:SerializedName("vitamin_A")
	val vitaminA: Int,

	@field:SerializedName("dan_lain_lain")
	val danLainLain: String,

	@field:SerializedName("vitamin_C")
	val vitaminC: Int,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("serat")
	val serat: Int,

	@field:SerializedName("jumlah_kalori")
	val jumlahKalori: Int,

	@field:SerializedName("lemak")
	val lemak: Int
)

data class ResepItem(

	@field:SerializedName("unit")
	val unit: String,

	@field:SerializedName("nama_bahan")
	val namaBahan: String,

	@field:SerializedName("takaran")
	val takaran: Int,

	@field:SerializedName("id")
	val id: Int
)
