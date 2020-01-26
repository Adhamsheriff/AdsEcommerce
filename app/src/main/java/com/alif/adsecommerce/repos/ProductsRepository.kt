package com.alif.adsecommerce.repos

import com.alif.adsecommerce.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            val json = URL("https://finepointmobile.com/data/products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }

    fun searchForProduct(term: String) {

    }

    fun getProductsPhotos() {

    }
}