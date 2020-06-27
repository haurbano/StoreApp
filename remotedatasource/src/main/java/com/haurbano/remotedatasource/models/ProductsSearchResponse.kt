package com.haurbano.remotedatasource.models

data class ProductsSearchResponse(
    val available_filters: List<Any>,
    val available_sorts: List<Any>,
    val filters: List<Any>,
    val paging: Paging,
    val query: String,
    val related_results: List<Any>,
    val results: List<SearchResult>,
    val secondary_results: List<Any>,
    val site_id: String,
    val sort: Sort
) {
    class Paging
    class Sort
}