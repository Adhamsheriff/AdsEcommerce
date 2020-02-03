package com.alif.adsecommerce.repos

import com.alif.adsecommerce.model.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class ProductsRepository {

    private fun retrofit() : ECommerceApi {
        return Retrofit.Builder()
            .baseUrl("https://finepointmobile.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ECommerceApi::class.java)
    }

    suspend fun fetchAllProductRetrofit() : List<Product>{
        return retrofit().fetchAllProduct()
    }

    suspend fun fetchProduct(productTitle: String) : Product{
        return fetchAllProductRetrofit().first{
            it.title == productTitle
        }
    }

    suspend fun searchForProduct(term: String): List<Product>  {
            return fetchAllProductRetrofit().filter {
                it.title.contains(term, true)
            }

    }
}