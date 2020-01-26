package com.alif.adsecommerce.repos

import com.alif.adsecommerce.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            val json = URL("https://gist.githubusercontent.com/Adhamsheriff/f4fdd5bbebee5d4cbba8c5e84bfffb33/raw/1fea1f05c0bff979ce21604e993e1ba21a2c9162/products_list.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }

    fun searchForProduct(term: String) {

    }

    fun getProductsPhotos() {

    }
}