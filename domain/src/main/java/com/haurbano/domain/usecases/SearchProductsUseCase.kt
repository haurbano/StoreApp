package com.haurbano.domain.usecases

import com.haurbano.domain.models.Product
import com.haurbano.domain.respositories.ProductsRepository

class SearchProductsUseCase (
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(query: String): List<Product>{
        return productsRepository.searchProductsBy(query)
    }
}