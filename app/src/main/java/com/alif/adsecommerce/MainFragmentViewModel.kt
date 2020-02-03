package com.alif.adsecommerce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alif.adsecommerce.model.Product
import com.alif.adsecommerce.repos.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel:ViewModel() {

    val products = MutableLiveData<List<Product>>()

    fun setup(){
        viewModelScope.launch(Dispatchers.Default){
            products.postValue(ProductsRepository().fetchAllProductRetrofit())
        }
    }

}