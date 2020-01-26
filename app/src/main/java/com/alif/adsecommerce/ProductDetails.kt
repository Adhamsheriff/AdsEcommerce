package com.alif.adsecommerce

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alif.adsecommerce.repos.ProductsRepository
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val title = intent.getStringExtra("title")

        val products = ProductsRepository().getProductByName(title)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                product_name.text = it.title
                product_price.text = "$${it.price}"
                Picasso.get().load(it.photoUrl).into(details_photo)
            }, {

            })





        details_availablity.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("Hey, $title is in Stock!")
                .setPositiveButton("Ok") { dialog, which ->
                }
                .create()
                .show()
        }
    }
}
