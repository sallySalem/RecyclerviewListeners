package com.kotlin.recyclerviewlisteners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_component.view.*

class ItemAdapter(
    private val items: List<String>,
    private val mListener: (Int) -> Unit
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_component, viewGroup, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.tv as TextView).text = "Item at ${items[position]}"
        (holder.checkBox as CheckBox).text = "CheckBox at ${items[position]}"
        (holder.switch as Switch).text = "Switch at ${items[position]}"
        (holder.radioButton as RadioButton).text = "RadioButton at ${items[position]}"

        holder.itemView.setOnClickListener {
            mListener.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.textView
        val checkBox = view.checkBox
        val switch = view.switch1
        val radioButton = view.radioButton
    }
}