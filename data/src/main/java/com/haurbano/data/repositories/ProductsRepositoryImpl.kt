package com.haurbano.data.repositories

import android.util.Log
import com.haurbano.data.datasources.ProductsRemoteDataSource
import com.haurbano.domain.common.ErrorEntity
import com.haurbano.domain.common.Resource
import com.haurbano.domain.models.ProductDetails
import com.haurbano.domain.models.ProductPreview
import com.haurbano.domain.respositories.ProductsRepository
import java.io.IOException
import java.lang.Exception

class ProductsRepositoryImpl(
    private val remoteDataSource: ProductsRemoteDataSource
) : ProductsRepository {
    companion object {
        private const val TAG = "ProductsRepositoryImpl"
    }

    override suspend fun searchProductsBy(query: String): Resource<List<ProductPreview>> {
        return try {
            val response = remoteDataSource.searchProductsBy(query)
            Resource.success(data = response)
        } catch (exc: IOException) {
            Log.w(TAG, exc.message!!)
            Resource.error(error = ErrorEntity.NetworkError)
        } catch (exc: Exception) {
            Log.w(TAG, exc.message!!)
            Resource.error(error = ErrorEntity.UnknownError)
        }
    }

    override suspend fun getProductDetails(productId: String): Resource<ProductDetails> {
        return try {
            val response = remoteDataSource.getProductDetails(productId)
            Resource.success(data = response)
        } catch (exc: IOException) {
            Log.w(TAG, exc.message!!)
            Resource.error(error = ErrorEntity.NetworkError)
        } catch (exc: Exception) {
            Log.w(TAG, exc.message!!)
            Resource.error(error = ErrorEntity.UnknownError)
        }
    }
}