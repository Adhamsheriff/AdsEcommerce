package com.alif.adsecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.alif.adsecommerce.model.Product
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val products = arrayListOf<Product>()
        for(i in 0..100){
            products.add(
                Product(
            "Shirt #$i",
                "https://via.placeholder.com/300.png/dddddd/000000",
                1.99
            )
            )

        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = ProductAdapter(products)
        }
    }
}
