package com.example.nutria.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    items: List<T>?,
    clickListener: BaseRecyclerAdapterClickListener<T>? = null
) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder<T>>() {

    interface BaseRecyclerAdapterClickListener<T> {
        fun onViewClicked(item: T, position: Int, actionId: Int? = 0)
    }

    protected val items: MutableList<T>
    protected val clickListener: BaseRecyclerAdapterClickListener<T>?

    init {
        this.items = (items?.toMutableList()) ?: mutableListOf()
        this.clickListener = clickListener
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: T? = null

        @CallSuper
        open fun bind(type: T) {
            item = type
        }
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    @CallSuper
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(items.get(position))
        holder.itemView.setOnClickListener {
            clickListener?.onViewClicked(items.get(position), position)
        }
    }

    fun addItems(items: List<T>) = this.items.addAll(items)

    fun clearItems() = items.clear()

    override fun getItemCount() = items.size
}