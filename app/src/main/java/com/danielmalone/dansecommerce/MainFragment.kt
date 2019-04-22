package com.danielmalone.dansecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.danielmalone.dansecommerce.database.AppDatabase
import com.danielmalone.dansecommerce.model.Product
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        val categories = listOf("Jeans", "Socks", "Suits", "Skirts", "Dresses", "Jeans", "Socks", "Pants", "Jackets", "Daniel")

        root.categoriesRecyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchButton.setOnClickListener {

            doAsync {

                val db = Room.databaseBuilder(
                        activity!!.applicationContext,
                        AppDatabase::class.java, "database-name"
                ).build()

                val productsFromDatabase = db.productDao().searchFor("%${searchTerm.text}%")

                val products = productsFromDatabase.map {
                    Product(
                            it.title,
                            "https://finepointmobile.com/data/jeans2.jpg",
                            it.price,
                            true
                    )
                }

                uiThread {
                    recycler_view.apply {
                        layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                        adapter = ProductsAdapter(products)
                    }
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}