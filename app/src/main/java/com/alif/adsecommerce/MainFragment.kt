package com.alif.adsecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.alif.adsecommerce.model.Product
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main,container,false)

        val products = arrayListOf<Product>()
        for (i in 0..100) {
            products.add(
                Product(
                    "Shirt #$i",
                    "https://via.placeholder.com/300.png/dddddd/000000",
                    1.99
                )
            )

        }

        root.recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductAdapter(products)
        }
        return root
    }


}