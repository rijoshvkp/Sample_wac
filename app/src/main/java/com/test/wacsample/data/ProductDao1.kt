package com.test.wacsample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao1 {
    @Insert
    suspend fun insertCategory(category: Category)
    @Insert
    suspend fun insertProduct(product: Product)
    @Insert
    suspend fun insertBanner(banner: Banner)

    @Query(value = "SELECT * FROM product")
    suspend fun getAllRecords(): List<Product>

    @Query(value = "SELECT * FROM category")
    suspend fun getAllCategories(): List<Category>

    @Query(value = "SELECT * FROM banner")
    suspend fun getAllBanners(): List<Banner>

    @Query(value = "DELETE FROM product")
    suspend fun deleteAllRecords()

    @Query(value = "DELETE FROM banner")
    suspend fun deleteAllBanner()

    @Query(value = "DELETE FROM category")
    suspend fun deleteAllCategory()
}