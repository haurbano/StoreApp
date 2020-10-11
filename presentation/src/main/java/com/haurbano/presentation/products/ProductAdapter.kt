package com.haurbano.presentation.products

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.haurbano.domain.models.ProductPreview
import com.haurbano.presentation.R
import com.haurbano.presentation.common.displayPrice
import com.haurbano.presentation.databinding.ItemProductBinding
import com.squareup.picasso.Picasso

class ProductAdapter(val listener: (ProductPreview, ImageView) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products = mutableListOf<ProductPreview>()

    fun updateProducts(newProductPreviews: List<ProductPreview>) {
        products.clear()
        products.addAll(newProductPreviews)
        notifyDataSetChanged()
    }

    class ViewHolder(val itemProductBinding: ItemProductBinding) : RecyclerView.ViewHolder(itemProductBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.itemProductBinding.root.setOnClickListener {
            listener(product, holder.itemProductBinding.productImage)
        }
        with(holder.itemProductBinding) {
            productTitle.text = product.title
            productPrice.text = product.price.displayPrice()
            Picasso.get()
                .load(product.thumbnail)
                .error(R.drawable.ic_broken_image_24)
                .into(this.productImage)
        }
    }
}