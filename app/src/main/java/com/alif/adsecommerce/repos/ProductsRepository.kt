package com.alif.adsecommerce.repos

import com.alif.adsecommerce.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            it.onSuccess(fetchProducts())
        }
    }

    fun searchForProduct(term: String): Single<List<Product>> {
        return Single.create<List<Product>> {
            val filterProduct = fetchProducts().filter {
                it.title.contains(term, true)
            }
            it.onSuccess(filterProduct)

        }
    }

    fun getProductByName(name: String): Single<Product> {
        return Single.create<Product> {
            val product = fetchProducts().first {
                it.title == name
            }
            it.onSuccess(product)

        }
    }

    fun getProductsPhotos() {

    }

    fun fetchProducts(): List<Product> {
        val json =
            URL("https://gist.githubusercontent.com/Adhamsheriff/f4fdd5bbebee5d4cbba8c5e84bfffb33/raw/1fea1f05c0bff979ce21604e993e1ba21a2c9162/products_list.json").readText()
        return Gson().fromJson(json, Array<Product>::class.java).toList()
    }
}