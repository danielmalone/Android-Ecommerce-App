package com.danielmalone.dansecommerce

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danielmalone.dansecommerce.repos.ProductsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

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

        val productsRepository = ProductsRepository().getAllProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    d("daniel", "success :)")
                    recycler_view.apply {
                        layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                        adapter = ProductsAdapter(it)
                    }
                    progressBar.visibility = View.GONE
                }, {
                    d("daniel", " error :( ${it.message}")
                })

//        searchButton.setOnClickListener {
//
//            doAsync {
//
//                val db = Room.databaseBuilder(
//                        activity!!.applicationContext,
//                        AppDatabase::class.java, "database-name"
//                ).build()
//
//                val productsFromDatabase = db.productDao().searchFor("%${searchTerm.text}%")
//
//                val products = productsFromDatabase.map {
//                    Product(
//                            it.title,
//                            "https://finepointmobile.com/data/jeans2.jpg",
//                            it.price,
//                            true
//                    )
//                }
//
//                uiThread {
//                    recycler_view.apply {
//                        layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
//                        adapter = ProductsAdapter(products)
//                    }
//                    progressBar.visibility = View.GONE
//                }
//            }
//        }
    }
}