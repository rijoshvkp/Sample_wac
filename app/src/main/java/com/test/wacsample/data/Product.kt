package com.test.wacsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class Product  (
    @SerializedName("id") val id: String? = null,//named as productId, productName... and productList --> products
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("actualPrice") val actualPrice:Int ? = null,
    @SerializedName("offerPrice") val offerPrice: Int? = null,
    @SerializedName("isExpress") val isExpress: Boolean? = null,
    @SerializedName("offerPercentage") val offerPercentage: Int? = null,
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
)