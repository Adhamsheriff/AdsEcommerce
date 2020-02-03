package com.alif.adsecommerce

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import com.alif.adsecommerce.cart.CartActivity
import com.alif.adsecommerce.database.AppDatabase
import com.alif.adsecommerce.database.CartModel
import com.alif.adsecommerce.database.ProductFromDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        doAsync {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null, "Socks Two Set", 1.00))
            val products = db.productDao().getAll()

            val cart = db.cartDao()
            cart.insertAll(CartModel(null, "Socks Two Set", 1.00,4))
            val allCartItem = cart.getAll()

            uiThread {

                d("Ads", "Product Size? ${products.size}")

                allCartItem.forEach{
                    d("Ads", "Item of cart? ${it.title} ${it.price} ${it.quantity}")
                }
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, MainFragment())
            .commit()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, MainFragment())
                        .commit()
                }
                R.id.actionJeans -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, JeansFragments())
                        .commit()
                }
                R.id.actionShorts -> d("Ads", "Shorts was Pressed!")
                R.id.actionAdmin -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout, AdminFragments())
                        .commit()
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()

            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.actionCart)
        {
            startActivity(Intent(this,CartActivity::class.java))
            return true
        }
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
}
