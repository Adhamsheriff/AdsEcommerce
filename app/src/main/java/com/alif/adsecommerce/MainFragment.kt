package com.alif.adsecommerce

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alif.adsecommerce.model.Product
import com.alif.adsecommerce.repos.ProductsRepository
import io.reactivex.Single
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
        loadRecyclerView(productsRepository)

        searchButon.setOnClickListener {
            loadRecyclerView(ProductsRepository().searchForProduct(searchTerm.text.toString()))

        }

    }

    fun loadRecyclerView(productsRepository: Single<List<Product>>) {
        val single = productsRepository
        .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductAdapter(it) { extaTitle, extaPhotoUrl, photoView ->
                        val intent = Intent(activity, ProductDetails::class.java)
                        intent.putExtra("title", extaTitle)
                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as AppCompatActivity,photoView, "photoToAnimate")
                        startActivity(intent, options.toBundle())
                    }
                }
                progressBar.visibility = View.GONE
            }, {

            })
    }
}