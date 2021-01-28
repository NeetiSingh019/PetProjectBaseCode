package eu.welfare.myapplication.data.network

import eu.welfare.myapplication.ui.startpage.MyProductItem
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    fun getProductList() :Call<List<MyProductItem>>
}