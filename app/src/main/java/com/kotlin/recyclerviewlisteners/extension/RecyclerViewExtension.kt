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

interface OnCheckedChangeListener {
    fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean, position: Int)
}

interface OnRadioGroupCheckedChangeListener {
    fun onCheckedChanged(group: RadioGroup, checkedId: Int, rowPosition: Int)
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
                    subview.setOnClickListener {
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

//RadioButton, Checkbox, Switch
fun RecyclerView.addOnCheckedChangeListener(onCheckedChangeListener: OnCheckedChangeListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            val holder = getChildViewHolder(view)
            for (index in 0 until (view as ViewGroup).childCount) {
                val subview = view.getChildAt(index)
                if(subview is CompoundButton) {
                    subview.setOnCheckedChangeListener { compoundButton, b ->
                        onCheckedChangeListener.onCheckedChanged(compoundButton, b, holder.adapterPosition)
                    }
                }
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            for (index in 0 until (view as ViewGroup).childCount) {
                val subview = view.getChildAt(index)
                if(subview is CompoundButton) {
                    subview.setOnCheckedChangeListener (null)
                }
            }
        }
    })
}

fun RecyclerView.addOnRadioGroupCheckedChangeListener(onRadioGroupCheckedChangeListener: OnRadioGroupCheckedChangeListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            val holder = getChildViewHolder(view)
            for (index in 0 until (view as ViewGroup).childCount) {
                val subview = view.getChildAt(index)
                if (subview is RadioGroup) {
                    subview.setOnCheckedChangeListener { group, buttonId ->
                        onRadioGroupCheckedChangeListener.onCheckedChanged(group, buttonId, holder.adapterPosition)
                    }
                }
            }
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            for (index in 0 until (view as ViewGroup).childCount) {
                val subview = view.getChildAt(index)
                if (subview is RadioGroup) {
                    subview.setOnCheckedChangeListener(null)
                }
            }
        }
    })
}