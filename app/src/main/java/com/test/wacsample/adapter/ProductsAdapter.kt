package com.test.wacsample.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.wacsample.R
import com.test.wacsample.databinding.EachProductBinding

import com.test.wacsample.model.Product
import com.test.wacsample.utils.downloadFromUrl
import com.test.wacsample.utils.placeholderProgressBar


class ProductsAdapter(
    private val products: ArrayList<Product>,
    private val context: Context,
) : RecyclerView.Adapter<ProductsAdapter.PlaceHolder>() {

    interface Listener {
        fun onItemClick(products: Product)//service : Service de alabilir.
    }

    class PlaceHolder(val binding: EachProductBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaceHolder {// layout ile bağlama işlemi, view binding ile
        val binding = DataBindingUtil.inflate<EachProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.each_product,
            parent,
            false
        )
        return PlaceHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PlaceHolder,
        position: Int
    ) {
        holder.binding.product = products[position]
        holder.binding.imageOfProduct.downloadFromUrl(
            products[position].image,
            placeholderProgressBar(holder.itemView.context)
        )
        holder.binding.textViewProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.ivTruck.visibility =
            if (products[position].isExpress == true) View.VISIBLE else View.INVISIBLE
        if (products[position].actualPrice == products[position].offerPrice) {
            holder.binding.textViewProductPriceOffer.visibility = View.INVISIBLE
        } else {
            holder.binding.textViewProductPriceOffer.visibility = View.VISIBLE
        }
        if (products[position].offerPercentage!! >0) {
            holder.binding.tvOffer.visibility = View.VISIBLE
            holder.binding.tvOffer.text = "${products[position]?.offerPercentage ?: 0} OFF"

        } else {
            holder.binding.tvOffer.visibility = View.GONE

        }

    }

    override fun getItemCount(): Int {
        return products.count()
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