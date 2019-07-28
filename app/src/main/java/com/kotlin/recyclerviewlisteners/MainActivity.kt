package com.kotlin.recyclerviewlisteners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 1..10){
            list.add("item $i")
        }

        val adapter = ItemAdapter(list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter
    }
}
