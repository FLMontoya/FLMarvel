package com.android.marvel.data.remote.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

private const val baseUrl = "http://gateway.marvel.com/v1/public/"
private const val publicKey = "6423a8f3425880101d292761eba17c25"
private const val privateKey = "32683f37ce287a0ee4b3fc8f9f0d9f47039c5fae"

@Singleton
class MarvelService @Inject constructor() {
    private val retrofit: Retrofit

    init {
        retrofit = initRetrofit()
    }

    private fun initRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(getAuthQueryInterceptor())
            .build()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    private fun getAuthQueryInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url
            val url = originalHttpUrl.newBuilder().apply {
                getQueryMap().forEach { queryParam ->
                    addQueryParameter(queryParam.key, queryParam.value)
                }
            }.build()

            val requestBuilder = originalRequest.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    /**
     * Authentication for Server-Side Applications
    Server-side applications must pass two parameters in addition to the apikey parameter:
    ts - a timestamp (or other long string which can change on a request-by-request basis)
    hash - a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
     */
    private fun getQueryMap(): Map<String, String> {
        return HashMap<String, String>().apply {
            val timeStamp = System.currentTimeMillis().toString()
            val input = timeStamp + privateKey + publicKey
            val md = MessageDigest.getInstance("MD5")
            val hash = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
            put("ts", timeStamp)
            put("apikey", publicKey)
            put("hash", hash)
        }
    }

    fun <SERVICE> createService(serviceClass: Class<SERVICE>): SERVICE {
        return retrofit.create(serviceClass)
    }


}