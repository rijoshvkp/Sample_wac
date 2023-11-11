package com.test.wacsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.test.wacsample.R
import com.test.wacsample.databinding.BannerRowitemBinding


import com.test.wacsample.model.Banner
import com.test.wacsample.utils.downloadFromUrl
import com.test.wacsample.utils.placeholderProgressBar


class BannerAdapter(
    private val category: ArrayList<Banner>,
    private val context: Context,
) : RecyclerView.Adapter<BannerAdapter.PlaceHolder>() {

    interface Listener {
        fun onItemClick(products: com.test.wacsample.data.Banner)//service : Service de alabilir.
    }

    class PlaceHolder(val binding: BannerRowitemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceHolder {// layout ile bağlama işlemi, view binding ile
        val binding = DataBindingUtil.inflate<BannerRowitemBinding>(LayoutInflater.from(parent.context), R.layout.banner_rowitem,parent,false)
        return PlaceHolder(binding)
    }
    override fun onBindViewHolder(
        holder: PlaceHolder,
        position: Int
    ) {
        holder.binding.imageOfBanner.downloadFromUrl(
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