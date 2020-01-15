package com.sample.kotlinsample.network

import com.sample.kotlinsample.model.EmployeeResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/api/users?page=2")
    fun getEmployees(): Call<EmployeeResponse>
}