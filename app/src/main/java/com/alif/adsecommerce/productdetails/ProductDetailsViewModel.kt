package com.alif.adsecommerce.productdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alif.adsecommerce.model.Product
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()

    fun fetchProductDetails(productTitle: String) {
        viewModelScope.launch(Dispatchers.Default) {
            val json =
                URL("https://gist.githubusercontent.com/Adhamsheriff/f4fdd5bbebee5d4cbba8c5e84bfffb33/raw/1fea1f05c0bff979ce21604e993e1ba21a2c9162/products_list.json").readText()
            val list = Gson().fromJson(json, Array<Product>::class.java).toList()
            val product = list.first { it.title == productTitle }

            productDetails.postValue(product)
        }
    }
}
