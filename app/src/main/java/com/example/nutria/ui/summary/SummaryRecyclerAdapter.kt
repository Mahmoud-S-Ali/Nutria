package com.example.nutria.ui.summary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nutria.R
import com.example.nutria.base.BaseRecyclerAdapter
import com.example.nutria.data.model.api.Ingredient
import com.example.nutria.utils.Utils
import kotlinx.android.synthetic.main.item_summary.view.*

class SummaryRecyclerAdapter(
    items: List<Ingredient>?,
    clickListener: BaseRecyclerAdapterClickListener<Ingredient>? = null
) : BaseRecyclerAdapter<Ingredient>(items, clickListener) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Ingredient> {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_summary, parent, false)

        return SummaryViewHolder(itemView)
    }

    private inner class SummaryViewHolder(itemView: View?) : BaseViewHolder<Ingredient>(itemView!!) {
        override fun bind(type: Ingredient) {
            super.bind(type)

            val parsedData = type?.ingredientsParsedData?.get(0)

            parsedData?.let {
                // We can use StringBuilder here for better performance
                itemView.tv_summary_title?.text =
                    Utils.getTruncatedFloatStr(parsedData.quantity) +
                            " " + (parsedData.unit ?: "") + " " + parsedData.name

                with(parsedData.nutrients?.energyData) {
                    itemView.tv_calories_value?.text =
                        Utils.getTruncatedFloatStr(this?.quantity ?: 0f) + " " + this?.unit
                }

                itemView.tv_weight_value.text = Utils.getTruncatedFloatStr(parsedData.weight) + " " + "g"
            }
        }
    }

    fun notifyWithNewItems(items: List<Ingredient>) {
        if (items.isEmpty())
            return

        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }
}