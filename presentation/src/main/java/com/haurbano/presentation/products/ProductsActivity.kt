package com.haurbano.presentation.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.haurbano.domain.common.Status.SUCCESS
import com.haurbano.domain.common.Status.ERROR
import com.haurbano.domain.common.Status.LOADING
import com.haurbano.presentation.R
import kotlinx.android.synthetic.main.activity_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        searchButton.setOnClickListener {
            searchProductBy()
        }
    }

    private fun searchProductBy() {
        viewModel.searchBy("apple").observe(this, Observer { products ->
            when (products.status) {
                SUCCESS -> Log.d("--haur", "Products: ${products.data?.size}")
                ERROR -> Log.e("--haur", "Error Products: ${products.message}")
                LOADING -> Log.d("--haur", "Loading products")
            }
        })
    }
}