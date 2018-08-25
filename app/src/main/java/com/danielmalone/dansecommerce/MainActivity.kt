package com.danielmalone.dansecommerce

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log.d
import android.view.MenuItem
import com.danielmalone.dansecommerce.model.Product
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> d("daniel", "Going home!")
                R.id.actionJeans -> {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, JeansFragment())
                            .commit()
                    d("daniel", "jeans was pressed!")
                }
                R.id.actionShorts -> {
                    d("daniel", "shorts was pressed!")
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }

        val products = arrayListOf<Product>()

        for (i in 0..100) {
            products.add(Product("Dress Shirt #$i", "http://via.placeholder.com/350/dddddd/000000", 1.99))
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductsAdapter(products)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
//        return super.onOptionsItemSelected(item)
    }
}
