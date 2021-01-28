package eu.welfare.myapplication.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "http://192.168.56.1:8080/"
  // private const val URL = "http://test-env-3.eba-rvqfjjtk.us-east-1.elasticbeanstalk.com/"


    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder()

    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Product service Interface


    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}