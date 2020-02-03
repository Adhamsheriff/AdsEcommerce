package com.alif.adsecommerce

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alif.adsecommerce.model.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    lateinit var mainFragmentViewModel: MainFragmentViewModel

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

        mainFragmentViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        mainFragmentViewModel.products.observe(requireActivity(), Observer {
            loadRecyclerView(it)
        })

        mainFragmentViewModel.setup()

        searchButon.setOnClickListener {
            mainFragmentViewModel.search(searchTerm.text.toString())
        }

        searchTerm.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                mainFragmentViewModel.search(searchTerm.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

    }

    private fun loadRecyclerView(products: List<Product>) {
        recycler_view.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = ProductAdapter(products) { extaTitle, extaPhotoUrl, photoView ->
                val intent = Intent(activity, ProductDetails::class.java)
                intent.putExtra("title", extaTitle)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity as AppCompatActivity,
                    photoView,
                    "photoToAnimate"
                )
                startActivity(intent, options.toBundle())
            }
        }
        progressBar.visibility = View.GONE

    }
}