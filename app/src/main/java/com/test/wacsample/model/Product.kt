package com.test.wacsample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseClass(

    val categories: List<Category>?,
    val banners: List<Banner>?,
    val products: List<Product>?
) : Parcelable

@Parcelize
data class Category(
    val id: String?,
    val image: String?,
    val name: String?
) : Parcelable

@Parcelize
data class Banner(
    val id: String?,
    val image: String?
) : Parcelable

@Parcelize
data class Product(
    val id: String?,
    val image: String?,
    val name: String?,
    val description: String?,
    val actualPrice: Int?,
    val offerPrice: Int?,
    val isExpress: Boolean?,
    val offerPercentage: Int?
) : Parcelable