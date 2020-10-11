package com.haurbano.presentation.products

import android.app.ActivityOptions
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
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
import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.databinding.ActivityProductsBinding
import com.haurbano.presentation.details.ProductDetailActivity
import org.koin.android.ext.android.inject
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
    private val errorMessageProvider: ErrorMessageProvider by inject()
    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupProductsAdapter()
        listenProductsChanges()
        checkSearchAction()
        showFirstMessage()
        addButtonsListener()
    }

    private fun addButtonsListener() {
        binding.imgUserFeedbackProducts.setOnClickListener {
            onSearchRequested()
        }
    }

    private fun getTransitionBundle(image: ImageView): Bundle {
        return ActivityOptions.makeSceneTransitionAnimation(
            this,
            image,
            getString(R.string.transition_detail_name)
        ).toBundle()
    }

    private fun checkSearchAction() {
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
                SUCCESS -> {
                    hideProgressBar()
                    updateProducts(products.data)
                }
                ERROR -> {
                    hideProgressBar()
                    val msg = errorMessageProvider.getMessageFor(products.error)
                    showErrorUI(msg)
                }
                LOADING -> showProgressBar()
            }
        })
    }

    private fun hideProgressBar() {
        binding.progressBarProducts.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.imgUserFeedbackProducts.visibility = View.GONE
        binding.txtUserFeedbackProducts.visibility = View.GONE
        binding.progressBarProducts.visibility = View.VISIBLE
    }

    private fun setupProductsAdapter() {
        val linearLayoutManager = GridLayoutManager(this, ITEMS_PER_ROW)
        binding.rvProducts.apply {
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
        binding.rvProducts.visibility = View.GONE
        binding.imgUserFeedbackProducts.setImageResource(R.drawable.ic_empty_box)
        binding.imgUserFeedbackProducts.visibility = View.VISIBLE
        binding.txtUserFeedbackProducts.text = getString(R.string.msg_empty_results)
        binding.txtUserFeedbackProducts.visibility = View.VISIBLE
    }

    private fun showFirstMessage() {
        binding.rvProducts.visibility = View.GONE
        binding.imgUserFeedbackProducts.setImageResource(R.drawable.ic_search_black_24dp)
        binding.imgUserFeedbackProducts.visibility = View.VISIBLE
        binding.txtUserFeedbackProducts.text = getString(R.string.first_message_user)
        binding.txtUserFeedbackProducts.visibility = View.VISIBLE
    }

    private fun showErrorUI(msg: String) {
        binding.rvProducts.visibility = View.GONE
        binding.imgUserFeedbackProducts.setImageResource(R.drawable.ic_error_24)
        binding.imgUserFeedbackProducts.visibility = View.VISIBLE
        binding.txtUserFeedbackProducts.text = msg
        binding.txtUserFeedbackProducts.visibility = View.VISIBLE
    }

    private fun showProductsList() {
        binding.rvProducts.visibility = View.VISIBLE
        binding.imgUserFeedbackProducts.visibility = View.GONE
        binding.txtUserFeedbackProducts.visibility = View.GONE
    }
}