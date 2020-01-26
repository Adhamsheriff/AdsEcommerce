package com.alif.adsecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alif.adsecommerce.repos.ProductsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)


        val categories =
            listOf("Shirts", "Jeans", "T-Shirts", "Pants", "Socks", "Categories1", "Categories2")

        root.categoriesRecycleView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productsRepository = ProductsRepository().getAllProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductAdapter(it)
                }
                progressBar.visibility = View.GONE
            }, {

            })

//        searchButon.setOnClickListener {
//            doAsync {
//                val db = Room.databaseBuilder(
//                    activity!!.applicationContext,
//                    AppDatabase::class.java, "database-name"
//                ).build()
//
//                val productFromDatabase = db.productDao().searchFor("%${searchTerm.text}%")
//                val products = productFromDatabase.map {
//                    Product(
//                        it.title,
//                        "https://finepointmobile.com/data/jeans1.jpg",
//                        it.price,
//                        true
//                    )
//                }
//
//                uiThread {
//                    recycler_view.apply {
//                        layoutManager = GridLayoutManager(activity, 2)
//                        adapter = ProductAdapter(products)
//                    }
//                    progressBar.visibility = View.GONE
//                }
//            }
//        }


    }
}