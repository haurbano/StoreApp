package com.haurbano.presentation.search.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.haurbano.domain.models.ProductPreview
import com.haurbano.presentation.common.composes.LoadingScreen
import com.haurbano.presentation.search.SearchResultScreenUIState
import com.haurbano.presentation.search.SearchScreenViewModel

@Composable
fun SearchScreen(viewModel: SearchScreenViewModel, productClicked: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    if (uiState.isLoading) { 
        LoadingScreen()
    } else {
        SearchResultContent(uiState = uiState, productClicked = productClicked)
    }
    
}

@Composable
fun SearchResultContent(uiState: SearchResultScreenUIState, productClicked: (String) -> Unit) {
    if (uiState.search.isEmpty()) {
        EmptyQueryView()        
    } else {
        ProductResultView(uiState = uiState, productClicked = productClicked)
    }
}

@Composable
fun EmptyQueryView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(text = "Type before search :D")
    }
}

@Composable
fun ProductResultView(uiState: SearchResultScreenUIState, productClicked: (String) -> Unit){
    Column(modifier = Modifier.padding(5.dp)) {
        QueryView(uiState)
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items(uiState.productsViews){ product ->
                ProductView(product = product, clicked = productClicked, modifier = Modifier.fillMaxSize().padding(top = 5.dp))
            }
        }
    }
}

@Composable
private fun QueryView(uiState: SearchResultScreenUIState) {
    Row(modifier = Modifier.padding(5.dp, 5.dp)) {
        Text(text = "Search: ")
        Text(text = uiState.search)
    }
}

@Composable
fun ProductView(modifier: Modifier, product: ProductPreview, clicked: (String) -> Unit) {
    Card(modifier= modifier) {
        Row(modifier = Modifier.clickable {clicked(product.id)} ) {
            Image(
                modifier= Modifier.height(70.dp),
                painter = rememberImagePainter(product.thumbnail),
                contentDescription = "Image thumbnail"
            )
            Column(modifier = Modifier.padding(5.dp, 5.dp)) {
                Text(text = product.title)
            }
        }
    }
}

