package com.skipthedishes.okhttp_cookie_test

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test

class SimpleInMemoryCookieJar: CookieJar {
    private val store: MutableList<Cookie> = mutableListOf()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return store.filter {
            !it.isExpired && it.matches(url)
        }
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        store.addAll(cookies)
    }

    private val Cookie.isExpired: Boolean
        get() = expiresAt < System.currentTimeMillis()
}

class OkHttpCookieUnitTest {
    private val interceptor = HttpLoggingInterceptor {
        println(it)
    }.apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Test
    fun fire_two_cookie_setting_requests_without_cookiejar() {

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()

        val request = Request.Builder()
            .url("https://httpbin.org/cookies/set/COOKIE/CHOCOLATE")
            .build()

        client.newCall(request).execute()
        client.newCall(request).execute()
    }

    @Test
    fun fire_two_cookie_setting_requests_with_a_cookiejar() {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .cookieJar(SimpleInMemoryCookieJar())
            .build()

        val request = Request.Builder()
            .url("https://httpbin.org/cookies/set/COOKIE/CHOCOLATE")
            .build()

        client.newCall(request).execute()
        client.newCall(request).execute()
    }
}