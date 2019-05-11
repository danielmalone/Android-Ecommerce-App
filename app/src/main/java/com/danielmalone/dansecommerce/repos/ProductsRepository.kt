package com.danielmalone.dansecommerce.repos

import com.danielmalone.dansecommerce.model.Product
import com.google.gson.Gson
import io.reactivex.Single
import java.net.URL

class ProductsRepository {

    fun getAllProducts(): Single<List<Product>> {
        return Single.create<List<Product>> {
            val json = URL("https://gist.githubusercontent.com/danielmalone/df34a33a06e985d85f2ba7f6e635c600/raw/df0aeaea2def4aa1c9b0f266d032b6733c3fbaaa/shopping_products.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }
}