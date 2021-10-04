package com.haurbano.presentation.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.haurbano.presentation.R
import com.haurbano.presentation.common.ErrorMessageProvider
import com.haurbano.presentation.details.ProductDetailActivity
import com.haurbano.presentation.search.view.SearchScreen
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    companion object {
        private const val SEARCH_QUERY_KEY = "search_query"
        private const val EMPTY_STRING = ""

        fun start(context: Context, query: String) {
            val intent = Intent(context, SearchActivity::class.java).apply {
                putExtra(SEARCH_QUERY_KEY, query)
            }
            context.startActivity(intent)
        }
    }

    private val viewModel: SearchScreenViewModel by viewModel()
    private val errorMessageProvider: ErrorMessageProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productClicked: (String) -> Unit = { id ->
            ProductDetailActivity.start(this, id)
        }
        setContent { SearchScreen(viewModel = viewModel, productClicked = productClicked) }
        checkSearchAction(intent)
    }

    private fun checkSearchAction(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            query?.let {
                if (it.isNotEmpty()) SearchActivity.start(this, query)
            }
        } else {
            val query = intent?.getStringExtra(SEARCH_QUERY_KEY)
            viewModel.searchBy(query ?: EMPTY_STRING)
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
}