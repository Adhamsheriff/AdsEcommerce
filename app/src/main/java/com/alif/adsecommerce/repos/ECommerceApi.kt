package com.alif.adsecommerce.repos

import com.alif.adsecommerce.model.Product
import retrofit2.http.GET

interface ECommerceApi {

    @GET("api/ecommerce/v1/allProducts")
    suspend fun fetchAllProduct(): List<Product>
}