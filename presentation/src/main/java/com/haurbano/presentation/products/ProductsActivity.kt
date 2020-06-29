package com.haurbano.presentation.products

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

    companion object {
        const val ITEMS_PER_ROW = 3
    }

    private val productClicked: (ProductPreview, ImageView) -> Unit = { product, image ->
        val options = getTransitionBundle(image)
        ProductDetailActivity.start(this, product.id, product.thumbnail, options)
    }

    private val viewModel: ProductViewModel by viewModel()
    private val productAdapter = ProductAdapter(productClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        setupProductsAdapter()
        listenProductsChanges()
        showFirstMessage()
    }

    private fun getTransitionBundle(image: ImageView): Bundle {
        return ActivityOptions.makeSceneTransitionAnimation(
            this,
            image,
            getString(R.string.transition_detail_name)
        ).toBundle()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            query?.let {
                if (it.isNotEmpty()) viewModel.searchBy(query)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search_product -> onSearchRequested()
        }
        return true
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
        val linearLayoutManager = GridLayoutManager(this, ITEMS_PER_ROW)
        rvProducts.apply {
            adapter = productAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun updateProducts(productPreviews: List<ProductPreview>?) {
        if (productPreviews == null || productPreviews.isEmpty()) {
            showEmptyResults()
        } else {
            showProductsList()
            productAdapter.updateProducts(productPreviews)
        }
    }

    private fun showEmptyResults() {
        rvProducts.visibility = View.GONE
        imgUserFeedbackProducts.setImageResource(R.drawable.ic_empty_box)
        imgUserFeedbackProducts.visibility = View.VISIBLE
        txtUserFeedbackProducts.text = getString(R.string.msg_empty_results)
        txtUserFeedbackProducts.visibility = View.VISIBLE
    }

    private fun showFirstMessage() {
        rvProducts.visibility = View.GONE
        imgUserFeedbackProducts.setImageResource(R.drawable.ic_search_black_24dp)
        imgUserFeedbackProducts.visibility = View.VISIBLE
        txtUserFeedbackProducts.text = getString(R.string.first_message_user)
        txtUserFeedbackProducts.visibility = View.VISIBLE
    }

    private fun showProductsList() {
        rvProducts.visibility = View.VISIBLE
        imgUserFeedbackProducts.visibility = View.GONE
        txtUserFeedbackProducts.visibility = View.GONE
    }
}