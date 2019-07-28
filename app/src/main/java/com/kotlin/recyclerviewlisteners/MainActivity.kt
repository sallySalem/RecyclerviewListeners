package com.kotlin.recyclerviewlisteners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.recyclerviewlisteners.extension.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..10) {
            list.add("item $i")
        }

        val adapter = ItemAdapter(list)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = adapter

        rvList.addOnSubItemClickListener(object : OnSubItemClickListener {
            override fun onSubItemClicked(position: Int, view: View) {
                Toast.makeText(this@MainActivity, "item at $position", Toast.LENGTH_LONG).show()
            }

        })
        
        rvList.addOnCheckedChangeListener(object : OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean, position: Int) {

                Toast.makeText(
                    this@MainActivity,
                    "CompoundButton at $position  _  ${buttonView.text}   _  state = $isChecked",
                    Toast.LENGTH_LONG
                ).show()
            }

        })

        rvList.addOnRadioGroupCheckedChangeListener(object: OnRadioGroupCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup, checkedId: Int, rowPosition: Int) {

                val rb = findViewById<View>(checkedId) as RadioButton
                Toast.makeText(this@MainActivity, "RadioGroup at $rowPosition  _  ${rb.text}", Toast.LENGTH_LONG).show()
            }

        })

    }
}
