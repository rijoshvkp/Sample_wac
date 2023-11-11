package com.test.wacsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.wacsample.R
import com.test.wacsample.databinding.CategoryRowitemBinding


import com.test.wacsample.utils.downloadFromUrl
import com.test.wacsample.utils.placeholderProgressBar
import com.test.wacsample.model.Category



class CategoriesshowAdapter(
    private val category: ArrayList<Category>,
    private val context: Context,
) : RecyclerView.Adapter<CategoriesshowAdapter.PlaceHolder>() {



    class PlaceHolder(val binding: CategoryRowitemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceHolder {// layout ile bağlama işlemi, view binding ile
        val binding = DataBindingUtil.inflate<CategoryRowitemBinding>(LayoutInflater.from(parent.context), R.layout.category_rowitem,parent,false)
        return PlaceHolder(binding)
    }
    override fun onBindViewHolder(
        holder: PlaceHolder,
        position: Int
    ) {
        holder.binding.category = category[position]
        holder.binding.imageOfcategory.downloadFromUrl(
            category[position].image,
            placeholderProgressBar(holder.itemView.context)
        )





    }
    override fun getItemCount(): Int {
        return category.count()
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }
    override fun onViewAttachedToWindow(holder: PlaceHolder) {
        super.onViewAttachedToWindow(holder)

    }
    override fun onViewRecycled(holder: PlaceHolder) {
        super.onViewRecycled(holder)

    }
}