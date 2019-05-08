package com.danielmalone.dansecommerce.repos

import com.danielmalone.dansecommerce.model.Product
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
}