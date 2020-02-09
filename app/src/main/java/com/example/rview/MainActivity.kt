package com.example.rview

import android.content.Context
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rview.services.OurNetwork
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject


class ProductItem(var productName:String, var productImage:String)

class ProductAdapter(var context: Context, var itemsList:List<ProductItem> ): RecyclerView.Adapter<ProductAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_product_item, null)
        val holder = Holder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return itemsList.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItems(position)
    }


    inner class Holder(view: View):RecyclerView.ViewHolder(view){
        val productImage = view.findViewById<ImageView>(R.id.productImage)
        val productText = view.findViewById<TextView>(R.id.productText)

        fun bindItems(position: Int){
            productText.text = itemsList[position].productName

            val imageResource = context.resources.getIdentifier(itemsList[position].productImage, "drawable", context.packageName)
            productImage.setImageResource(imageResource)

        }
    }

}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ourList = listOf( ProductItem("1", "a") , ProductItem("2", "b"), ProductItem("3", "c") , ProductItem("1", "a"), ProductItem("1", "a") )

        val adapter = ProductAdapter(this, ourList)
        productList.adapter = adapter
        //productList.layoutManager = LinearLayoutManager(this)
        var spanCount = 2
        if( resources.configuration.orientation == ORIENTATION_LANDSCAPE )
            spanCount = 3

        val url = "https://jsonplaceholder.typicode.com/posts"
        OurNetwork.getPost(this, url , { array->

            (0..(array.length()-1)).forEach{
                val currentId = (array[it] as JSONObject).get("id")
                println(currentId)
            }
            webText.text = array.length().toString()}
        )

        productList.layoutManager = GridLayoutManager(this, spanCount)

    }


}
