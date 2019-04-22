package com.danielmalone.dansecommerce.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductFromDatabase")
    fun getAll(): List<ProductFromDatabase>

    @Query("SELECT * FROM ProductFromDatabase WHERE title LIKE :term")
    fun searchFor(term: String): List<ProductFromDatabase>

    @Insert
    fun insertAll(vararg products: ProductFromDatabase)
}
