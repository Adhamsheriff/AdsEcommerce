package com.alif.adsecommerce

import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.alif.adsecommerce.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> d("Ads", "Home was Pressed!")
                R.id.actionJeans -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, JeansFragments())
                        .commit()
                    d("Ads", "Jeans was Pressed!")
                }
                R.id.actionShorts -> d("Ads", "Shorts was Pressed!")
            }
            it.isChecked = true
            drawerLayout.closeDrawers()

            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }

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

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductAdapter(products)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
//        return super.onOptionsItemSelected(item)
    }
}
