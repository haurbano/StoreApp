package com.haurbano.presentation.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.haurbano.domain.common.Status.SUCCESS
import com.haurbano.domain.common.Status.ERROR
import com.haurbano.domain.common.Status.LOADING
import com.haurbano.domain.models.Product
import com.haurbano.presentation.R
import kotlinx.android.synthetic.main.activity_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModel()
    private val productAdapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setupProductsAdapter()
        searchButton.setOnClickListener {
            searchProductBy()
        }
    }

    private fun setupProductsAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        rvProducts.apply {
            adapter = productAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun searchProductBy() {
        viewModel.searchBy("apple").observe(this, Observer { products ->
            when (products.status) {
                SUCCESS -> updateProducts(products.data)
                ERROR -> Log.e("--haur", "Error Products: ${products.message}")
                LOADING -> Log.d("--haur", "Loading products")
            }
        })
    }

    private fun updateProducts(products: List<Product>?) {
        if (products == null || products.isEmpty()) {
            showEmptyResults()
        } else {
            productAdapter.updateProducts(products)
        }
    }

    private fun showEmptyResults() {
        // Shoe empty view later
    }
}