package com.haurbano.presentation.home

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import com.haurbano.presentation.R
import com.haurbano.presentation.search.SearchActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkSearchAction(intent)
        setContent { HomeScreen() }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkSearchAction(intent)
    }

    private fun checkSearchAction(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            query?.let {
                if (it.isNotEmpty()) SearchActivity.start(this, query)
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
}