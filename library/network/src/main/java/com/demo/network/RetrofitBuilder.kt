package com.demo.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

private const val BASE_URL = "https://itunes.apple.com/"
fun buildOkHttpClient(): OkHttpClient {

    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)

    return builder.build()
}

@ExperimentalSerializationApi
fun buildRetrofit(client: OkHttpClient): Retrofit {
    val build = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(defaultConverter())
        .build()
    return build
}


@ExperimentalSerializationApi
fun defaultConverter(): Converter.Factory {
    val contentType = "application/json".toMediaType()
    return JsonConverted.builder.asConverterFactory(contentType)
}

object JsonConverted {
    val builder = Json(builderAction = {
        coerceInputValues = true
        ignoreUnknownKeys = true
        this.isLenient = true
    })
}