package com.haurbano.presentation.products

import android.app.ActivityOptions
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.haurbano.domain.common.Status.*
import com.haurbano.domain.models.ProductPreview
import com.haurbano.presentation.R
import com.haurbano.presentation.details.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsActivity : AppCompatActivity() {

    private val productClicked: (ProductPreview, ImageView) -> Unit = { product, image ->
        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            image,
            getString(R.string.transition_detail_name)
        )
        ProductDetailActivity.start(this, product.id, product.thumbnail, options.toBundle())
    }

    private val viewModel: ProductViewModel by viewModel()
    private val productAdapter = ProductAdapter(productClicked)
    private var query = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setupProductsAdapter()
        listenProductsChanges()
        searchButton.setOnClickListener {
            searchProductBy()
        }
    }

    private fun listenProductsChanges() {
        viewModel.getProducts().observe(this, Observer { products ->
            when (products.status) {
                SUCCESS -> updateProducts(products.data)
                ERROR -> Log.e("--haur", "Error Products: ${products.message}")
                LOADING -> Log.d("--haur", "Loading products")
            }
        })
    }

    private fun setupProductsAdapter() {
        val linearLayoutManager = GridLayoutManager(this, 3)
        rvProducts.apply {
            adapter = productAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun searchProductBy() {
        query = "apple"
        viewModel.searchBy("apple")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle().apply { putString("query", query) }
        outState.putBundle("search", bundle)
    }

    private fun updateProducts(productPreviews: List<ProductPreview>?) {
        if (productPreviews == null || productPreviews.isEmpty()) {
            showEmptyResults()
        } else {
            productAdapter.updateProducts(productPreviews)
        }
    }

    private fun showEmptyResults() {
        // Shoe empty view later
    }
}