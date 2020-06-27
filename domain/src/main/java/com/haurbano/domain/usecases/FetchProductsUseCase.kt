package com.haurbano.domain.usecases

import com.haurbano.domain.models.Product
import com.haurbano.domain.respositories.ProductsRepository

class FetchProductsUseCase (
    private val productsRepository: ProductsRepository
) {
    suspend operator fun invoke(): List<Product>{
        return productsRepository.getProducts()
    }
}