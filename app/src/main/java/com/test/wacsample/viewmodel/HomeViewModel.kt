package com.test.wacsample.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.wacsample.data.ProductDatabases
import com.test.wacsample.model.Banner
import com.test.wacsample.model.Category
import com.test.wacsample.model.DataItem

import com.test.wacsample.model.Product
import com.test.wacsample.model.ResponseItem
import com.test.wacsample.service.ProductsAPI
import com.test.wacsample.utils.network.ConnectivityObserver
import com.test.wacsample.view.MainActivity
import isNetworkAvailable
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {
    private val _products = MutableLiveData<ArrayList<Product>?>()
    val products get() = _products

    private val _category = MutableLiveData<ArrayList<Category>?>()
    val category get() = _category

    private val _banner = MutableLiveData<ArrayList<Banner>?>()
    val banner get() = _banner
    private lateinit var connectivityObserver: ConnectivityObserver

    fun getData(context: Context) {

        if (isNetworkAvailable(context)) {
            getDataFromAPI(context)
        } else {
            getDataFromSQLite(context)
            getDataFromSQLiteCategory(context)
            getDataFromSQLiteBanner(context)
        }

    }

    private fun getDataFromAPI(context: Context){
        val retrofit = Retrofit
            .Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ProductsAPI::class.java)
        val call = service.getData()
        call.enqueue(object : Callback<List<ResponseItem>> {
            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ) {
                storeInSQLiteCategory(context, response.body()?.get(0)?.data!!)
                storeInSQLiteBanner(context, response.body()?.get(1)?.data!!)
                storeInSQLiteProduct(context, response.body()?.get(2)?.data!!)

            }
        })
    }



    private fun storeInSQLiteCategory(context: Context, category: List<DataItem?>) {
        viewModelScope.launch {
            val productDb = ProductDatabases.invoke(context).productDao()
            productDb.deleteAllCategory()

            category.forEach {
                val categorys = com.test.wacsample.data.Category(it?.id,it?.image,it?.name)
                productDb.insertCategory(categorys)
            }
            getDataFromSQLiteCategory(context)
        }
    }
    private fun storeInSQLiteBanner(context: Context, data: List<DataItem?>) {
        viewModelScope.launch {
            val productDb = ProductDatabases.invoke(context).productDao()
            productDb.deleteAllBanner()

            data.forEach {
                val banner = com.test.wacsample.data.Banner(it?.id,it?.image)
                productDb.insertBanner(banner)
            }
            getDataFromSQLiteBanner(context)
        }

    }



    fun storeInSQLiteProduct(context: Context, products: List<DataItem?>) {
        viewModelScope.launch {
            val productDb = ProductDatabases.invoke(context).productDao()
            productDb.deleteAllRecords()

                products.forEach {
                    val aProduct = com.test.wacsample.data.Product(it?.id,it?.image,it?.name,it?.description,it?.actualPrice,it?.offerPrice,it?.isExpress,it?.offerPercentage)
                    productDb.insertProduct(aProduct)
                }
            getDataFromSQLite(context)
        }

    }
    private fun getDataFromSQLite(context: Context) {
        viewModelScope.launch {
            val productDb = ProductDatabases(context).productDao().getAllRecords()
            val products1 = arrayListOf<Product>()
            productDb.forEach{

                val list = Product(it.id,it.image,it.name,it.description,it.actualPrice,it.offerPrice,it.isExpress,it.offerPercentage)
                products1.add(list)
            }
            _products.postValue(products1)
        }
    }

    private fun getDataFromSQLiteCategory(context: Context) {
        viewModelScope.launch {
            val categoryDb = ProductDatabases(context).productDao().getAllCategories()
            val categoryDb1 = arrayListOf<Category>()
            categoryDb.forEach{
                val list = Category(it.id,it.image,it.name)
                categoryDb1.add(list)
            }
            _category.postValue(categoryDb1)
        }
    }

    private fun getDataFromSQLiteBanner(context: Context) {
        viewModelScope.launch {
            val bannerDb = ProductDatabases(context).productDao().getAllBanners()
            val bannerDb1 = arrayListOf<Banner>()
            bannerDb.forEach{
                val list = Banner(it.id,it.image)
                bannerDb1.add(list)
            }
            _banner.postValue(bannerDb1)
        }
    }
}