package com.sample.kotlinsample.model

data class EmployeeResponse(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)