package com.alif.adsecommerce.model

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("name")
    val title: String,
    @SerializedName("photo_url")
    val photoUrl: String,

    val price:Double
)
