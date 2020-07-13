package com.haurbano.domain.usecases

import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.respositories.ProductsRepository

class SearchProductsUseCase(
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(query: String): Resource<List<ProductPreview>> {
        return productsRepository.searchProductsBy(query)
    }
}