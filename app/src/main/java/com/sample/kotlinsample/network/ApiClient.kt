package com.sample.kotlinsample.network

import com.sample.kotlinsample.util.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {


//    private const val BASE_URL = "http://192.168.43.207/myapi/public/"
//
//    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor { chain ->
//            val original = chain.request()
//
//            val requestBuilder = original.newBuilder()
//                .method(original.method(), original.body())
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }.build()
//
//    val instance: ApiService by lazy{
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        retrofit.create(ApiService::class.java)
//    }

//    companion object {
//
//        private val okHttpInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BASIC)
//
//        private val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(okHttpInterceptor)
//            .build()
//
//        private var retrofit = Retrofit.Builder()
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(Constant.BASE_URL)
//            .build()
//
//        var apiUrl: ApiService = retrofit.create(ApiService::class.java)
//    }


    companion object {

        private val okHttpInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(okHttpInterceptor)
            .build()

        fun create(): ApiService {

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}