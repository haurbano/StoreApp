package com.haurbano.presentation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.haurbano.domain.common.Status
import com.haurbano.domain.models.ProductDetails
import com.haurbano.presentation.R
import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.common.TransitionStatus
import com.haurbano.presentation.common.displayPrice
import com.haurbano.presentation.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class ProductDetailActivity : AppCompatActivity() {

    private val viewModel: ProductDetailViewModel by viewModel()
    private val errorMessageProvider: ErrorMessageProvider by inject()
    private var imageTransitionStatus: TransitionStatus = TransitionStatus.UNDEFINED
    private val pendingActions = ArrayList<()-> Unit>()
    private val featuresAdapter = FeaturesAdapter()
    private lateinit var binding: ActivityProductDetailBinding

    companion object {
        private const val PRODUCT_ID_KEY = "PRODUCT_ID_KEY"
        private const val THUMBNAIL_KEY = "THUMBNAIL_KEY"

        fun start(
            context: Context,
            productId: String,
            thumbnail: String = "",
            bundleTransaction: Bundle? = null
        ) {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra(PRODUCT_ID_KEY, productId)
            intent.putExtra(THUMBNAIL_KEY, thumbnail)
            context.startActivity(intent, bundleTransaction)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenProductDetailsChanges()
        fetchProductDetails()
        showTemporalThumbnail()
        setupFeaturesAdapter()
    }

    private fun setupFeaturesAdapter() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.productDetailsContainer.rvFeatures.apply {
            layoutManager = linearLayoutManager
            adapter = featuresAdapter
        }
    }

    private fun showTemporalThumbnail() {
        val thumbnail = intent.getStringExtra(THUMBNAIL_KEY)
        displayProductImage(thumbnail)
    }

    private fun fetchProductDetails() {
        val productId = intent.getStringExtra(PRODUCT_ID_KEY) ?: ""
        viewModel.fetchProductDetails(productId)
    }

    private fun listenProductDetailsChanges() {
        viewModel.getProduct().observe(this, Observer { product ->
            when (product.status) {
                Status.SUCCESS -> renderProductDetails(product.data)
                Status.LOADING -> showLoadingUI()
                Status.ERROR -> {
                    val message = errorMessageProvider.getMessageFor(product.error)
                    showErrorUI(message)
                }
            }
        })
    }

    private fun showErrorUI(message: String) {
        binding.progressBarDetails.visibility = View.GONE
        binding.productDetailsContainer.root.visibility = View.GONE
        binding.imgUserFeedbackDetails.visibility = View.VISIBLE
        binding.txtMsgUserFeedbackDetails.visibility = View.VISIBLE

        binding.imgUserFeedbackDetails.setImageResource(R.drawable.ic_error_24)
        binding.txtMsgUserFeedbackDetails.text = message
    }

    private fun showLoadingUI() {
        binding.productDetailsContainer.root.visibility = View.GONE
        binding.progressBarDetails.visibility = View.VISIBLE
    }

    private fun renderProductDetails(details: ProductDetails?) {
        binding.progressBarDetails.visibility = View.GONE
        binding.productDetailsContainer.root.visibility = View.VISIBLE
        binding.imgUserFeedbackDetails.visibility = View.GONE
        binding.txtMsgUserFeedbackDetails.visibility = View.GONE

        details?.let {
            replaceThumbnail(it.images.first())
            binding.productDetailsContainer.tvProductDetailsTitle.text = it.title
            binding.productDetailsContainer.txtProductDetailPrice.text = getString(R.string.msg_product_price, it.price.displayPrice())
            val condition: String = it.condition.capitalize()

            // This is needed because a bug in the lint AS task, the lint checker needs to know this is a Integer
            val soldQuantity: Int = it.soldQuantity
            binding.productDetailsContainer.txtConditionSellsInfo.text = getString(R.string.msg_condition_sell_info, condition, soldQuantity)
            binding.productDetailsContainer.txtProductDetilDescription.text = saveInfo(it.description)
            featuresAdapter.updateFeatures(it.features)
        }
    }

    private fun saveInfo(info: String?): String {
        return if (info != null && info.isNotEmpty()) {
            info
        } else {
            getString(R.string.msg_info_no_available)
        }
    }

    private fun replaceThumbnail(url: String) {
        when (imageTransitionStatus) {
            TransitionStatus.STARTED -> pendingActions.add { displayProductImage(url) }
            else -> displayProductImage(url)
        }
    }

    private fun displayProductImage(url: String?) {
        url?.let {
            Picasso.get()
                .load(it)
                .noFade()
                .noPlaceholder()
                .error(R.drawable.ic_broken_image_24)
                .into(binding.productDetailImage)
        }
    }
}