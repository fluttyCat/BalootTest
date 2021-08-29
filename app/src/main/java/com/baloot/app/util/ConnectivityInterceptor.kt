package com.baloot.app.util

import android.app.Application
import android.net.ConnectivityManager
import com.baloot.app.util.NetworkUtil.isOnline
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ConnectivityInterceptor @Inject constructor(
    private val connectivityManager: ConnectivityManager,
    val app: Application
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //oAuth 2
        // access token
        // refresh token

        if (!isOnline(connectivityManager)) {
            throw NoConnectivityException()
        }

        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-type", "application/json")

        //if code == 404 -> then call refresh token api
        //x -> 404
        //        refres call -> 200
        //        again call x -> 200
        //        reture x response



        return chain.proceed(requestBuilder.build())
    }

}
