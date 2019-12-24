package com.danielmalone.dansecommerce.repos

import com.danielmalone.dansecommerce.model.Product
import retrofit2.http.GET

interface EcommerceApi {

    @GET("api/ecommerce/v1/allProducts")
    suspend fun fetchAllProducts(): List<Product>
}
