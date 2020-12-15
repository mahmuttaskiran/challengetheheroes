package com.mamudo.challengetheheroes.di

import com.mamudo.challengetheheroes.BuildConfig
import com.mamudo.challengetheheroes.api.MarvelApi
import com.mamudo.challengetheheroes.utils.md5
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val MARVEL_API_BASE_URL = "http://gateway.marvel.com/public/"

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MARVEL_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val defaultReq = chain.request()
            val md5HashedSignature =
                "1${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_PUBLIC_KEY}".md5()
            val defaultUrl = defaultReq.url()
            val httpUrl = defaultUrl.newBuilder()
                .addQueryParameter("ts", "1")
                .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_KEY)
                .addQueryParameter("hash", md5HashedSignature)
                .build()
            val reqBuilder = defaultReq.newBuilder().url(httpUrl)
            chain.proceed(reqBuilder.build())
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(interceptor: Interceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
            .addInterceptor(interceptor)
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }
}