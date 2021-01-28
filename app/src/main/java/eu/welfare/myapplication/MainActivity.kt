package eu.welfare.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import eu.welfare.myapplication.data.network.ProductService
import eu.welfare.myapplication.data.network.ServiceBuilder
import eu.welfare.myapplication.databinding.ActivityMainBinding
import eu.welfare.myapplication.ui.adapterclasses.ProductAdapter
import eu.welfare.myapplication.ui.startpage.MyProductItem
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        Toast.makeText(this@MainActivity,"Before b}", Toast.LENGTH_LONG).show()
        setContentView(binding.root)
        Toast.makeText(this@MainActivity,"after nb", Toast.LENGTH_LONG).show()
        loadProductsList()
    }

    private fun loadProductsList() {
        // Initiate The Service
        val destinationService = ServiceBuilder.buildService(ProductService::class.java)
        val requestCall = destinationService.getProductList()

        // Make Network call asynchronously
        requestCall.enqueue(object : retrofit2.Callback<List<MyProductItem>> {
            override fun onResponse(call: Call<List<MyProductItem>>, response: Response<List<MyProductItem>>) {
               Toast.makeText(this@MainActivity,"response ${response.body()}", Toast.LENGTH_LONG).show()
                Log.d("Response", "onResponse: ${response.body()}")
                if (response.isSuccessful){
                    val countryList  = response.body()!!
                    Log.d("Response", "countrylist size : ${countryList.size}")


                    binding.recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(this@MainActivity,2)
                        adapter = ProductAdapter(response.body()!!)
                    }
                }else{
                    Toast.makeText(this@MainActivity, "Something went wrong ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
            @Override
            override fun onFailure(call: Call<List<MyProductItem>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t", Toast.LENGTH_SHORT).show()
            }
        })
    }
}