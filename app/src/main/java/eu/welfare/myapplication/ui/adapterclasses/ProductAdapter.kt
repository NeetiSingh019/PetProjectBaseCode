package eu.welfare.myapplication.ui.adapterclasses

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.welfare.myapplication.R
import eu.welfare.myapplication.ui.startpage.MyProductItem



class ProductAdapter(private val productList: List<MyProductItem>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return productList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${productList.size} ")


        return holder.bind(productList[position])

    }
    class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {


        var tbFlag: TextView = itemView.findViewById<TextView>(R.id.ivFlag)
        var tvTitle: TextView = itemView.findViewById<TextView>(R.id.tvTitle)

        fun bind(product: MyProductItem) {

          //  val name ="Cases :${product.cases.toString()}"
            tvTitle.text = product.productname
            tbFlag.text = product.id.toString()
            //Picasso.get().load(country.countryInfo.flag).into(imageView)
        }

    }
}