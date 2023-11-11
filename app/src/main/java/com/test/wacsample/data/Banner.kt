package com.test.wacsample.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "banner")
data class Banner  (
    @SerializedName("id") val id: String? = null,//named as productId, productName... and productList --> products
    @SerializedName("image") val image: String? = null,
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
)