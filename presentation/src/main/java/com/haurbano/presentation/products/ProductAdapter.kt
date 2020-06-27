package com.haurbano.presentation.products

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haurbano.domain.models.Product
import com.haurbano.presentation.R
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var products = mutableListOf<Product>()

    fun updateProducts(newProducts: List<Product>) {
        products.clear()
        products.addAll(newProducts)
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
        with(holder.view) {
            productTitle.text = product.title
            productPrice.text = product.price.toString()
        }
    }
}