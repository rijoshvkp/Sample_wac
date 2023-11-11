package com.test.wacsample.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class,Category::class,Banner::class], version = 1)
abstract class ProductDatabases : RoomDatabase() {
    abstract fun productDao(): ProductDao1
    companion object {
        //Singleton
        @Volatile
        private var instance: ProductDatabases? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ProductDatabases::class.java, "product"
        ).build()
    }
}
