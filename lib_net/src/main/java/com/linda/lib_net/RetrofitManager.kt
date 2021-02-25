package com.linda.lib_net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 描述 :     Retrofit管理类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
class RetrofitManager {

    var apiService: ApiService? = null

    companion object {
        const val DEFAULT_TIME_OUT: Long = 60000
        val instance = Holder.holder
    }

    private object Holder {
        val holder = RetrofitManager()
    }

    private constructor() {

        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val headerInterceptor = Interceptor { chain ->
            val build = chain.request().newBuilder().build()
            chain.proceed(build)
        }

        // 创建一个不验证证书链的TrustManager
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {

                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {

                }

                override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                    return arrayOf()
                }
            }
        )
        // 使用该TrustManager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // 创建SSLSocketFactory
        val sslSocketFactory = sslContext.socketFactory

        val okHttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { hostname, session -> true }
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.MICROSECONDS)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.MICROSECONDS)
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MICROSECONDS)
            .addInterceptor(logInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Config.BASE_URL)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


    class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Log.e("OkHttp", "message:   " + message)
        }
    }
}