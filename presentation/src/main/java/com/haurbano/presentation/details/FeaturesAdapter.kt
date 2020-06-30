package com.haurbano.presentation.details

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haurbano.domain.models.ProductFeature

class FeaturesAdapter : RecyclerView.Adapter<FeaturesAdapter.ViewHolder>() {

    private val features = ArrayList<ProductFeature>()

    class ViewHolder(val view: TextView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TextView(parent.context)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = features.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.text = " - ${features[position].text}"
    }

    fun updateFeatures(list: List<ProductFeature>) {
        features.clear()
        features.addAll(list)
        notifyDataSetChanged()
    }
}