package com.danielmalone.dansecommerce

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.danielmalone.dansecommerce.database.AppDatabase
import com.danielmalone.dansecommerce.database.ProductFromDatabase
import kotlinx.android.synthetic.main.fragment_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class AdminFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton.setOnClickListener {
            val title = productTitle.text
            d("daniel", "button pressed :) with text of $title")

            doAsync {

                val db = Room.databaseBuilder(
                        activity!!.applicationContext,
                        AppDatabase::class.java, "database-name"
                ).build()

                db.productDao().insertAll(ProductFromDatabase(null, title.toString(), 12.34))

                uiThread {
                    d("daniel", "redirecting to home screen")
                }
            }
        }
    }
}
