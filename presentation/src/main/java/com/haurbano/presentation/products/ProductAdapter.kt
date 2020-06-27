package com.haurbano.presentation.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haurbano.domain.models.ProductPreview
import com.haurbano.presentation.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(val listener: (ProductPreview) -> Unit) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products = mutableListOf<ProductPreview>()

    fun updateProducts(newProductPreviews: List<ProductPreview>) {
        products.clear()
        products.addAll(newProductPreviews)
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.view.setOnClickListener { listener(product) }
        with(holder.view) {
            productTitle.text = product.title
            productPrice.text = product.price.toString()
            Picasso.get()
                .load(product.thumbnail)
                .error(R.drawable.ic_broken_image_24)
                .into(this.productImage)
        }
    }
}