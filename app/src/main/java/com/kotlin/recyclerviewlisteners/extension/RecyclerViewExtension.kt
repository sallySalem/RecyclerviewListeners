package com.kotlin.recyclerviewlisteners.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup


interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}


interface OnSubItemClickListener {
    fun onSubItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }
    })
}

fun RecyclerView.addOnSubItemClickListener(onClickListener: OnSubItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
                val holder = getChildViewHolder(view)
                for (index in 0 until (view as ViewGroup).childCount) {
                    val subview = view.getChildAt(index)
                    view.setOnClickListener {
                    onClickListener.onSubItemClicked(holder.adapterPosition, subview)
                }
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            for (index in 0 until (view as ViewGroup).childCount) {
                val subview = view.getChildAt(index)
                subview.setOnClickListener(null)
            }
        }
    })
}