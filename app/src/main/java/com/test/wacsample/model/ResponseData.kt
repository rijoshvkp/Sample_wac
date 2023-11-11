package com.test.wacsample.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseData(

	@field:SerializedName("Response")
	val response: List<ResponseItem?>? = null
) : Parcelable

@Parcelize
data class ResponseItem(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("categoryImage")
	val categoryImage: String? = null,

	@field:SerializedName("offerPrice")
	val offerPrice: Int? = null,

	@field:SerializedName("actualPrice")
	val actualPrice: Int? = null,

	@field:SerializedName("offerPercentage")
	val offerPercentage: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("isExpress")
	val isExpress: Boolean? = null
) : Parcelable
