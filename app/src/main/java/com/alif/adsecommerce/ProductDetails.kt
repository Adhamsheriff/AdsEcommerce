package com.alif.adsecommerce

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val title = intent.getStringExtra("title")
        val photo_url = intent.getStringExtra("photo_url")
        product_name.text = title
        Picasso.get().load(photo_url).into(details_photo)


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
