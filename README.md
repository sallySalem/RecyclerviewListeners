# RecyclerviewListeners
Show how to use extension function to handle recyclerView item and subItem listeners.


![](https://github.com/sallySalem/RecyclerviewListeners/blob/master/listener_1.png)

There are different ways to handle recyclerView item click listener, today I am going to use kotlin extension function to handle the item click listener and different listeners for sub view in the recycleView cell.

#####  * Using Extenstion fun

   * Row (item) click listener
   * SubView click listener
   * CompoundButton onCheckedChanged listener [Code](https://github.com/sallySalem/RecyclerviewListeners/commit/cc6d08c217907ad93027264db2da5b7afb3fc01c) 
          
```kotlin
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
```
   * RadioGroup onCheckedChanged listener [Code](https://github.com/sallySalem/RecyclerviewListeners/commit/863c4aae342b878f186fd23a49aec536742ec4c4)
          
```Kotlin
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
```


#####  * Using inline fun  [Full Code](https://github.com/sallySalem/RecyclerviewListeners/pull/3/commits/3d6422cfc86a60537ded6ac518a6aaae16bf6c3c)


#####  * Memory impact

