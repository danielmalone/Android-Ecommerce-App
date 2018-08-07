package com.danielmalone.dansecommerce

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        product_name.text = title

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("Hey, $title is in stock!")
                    .setPositiveButton("OK") { p0, p1 ->

                    }
                    .create()
                    .show()
        }
    }
}