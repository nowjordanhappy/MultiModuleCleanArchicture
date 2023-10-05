package com.devsu.buildsrc

object Retrofit {
    const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"

    private const val okHttpVersion = "4.10.0"
    const val okHttpBmo = "com.squareup.okhttp3:okhttp-bom:$okHttpVersion"
    const val okHttp = "com.squareup.okhttp3:okhttp"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor"

}