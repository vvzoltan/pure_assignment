package com.vvz.pure.test.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vvz.pure.test.data.remote.api.CocktailApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@Module
open class RetrofitModule {

    protected open val baseUrl = "https://www.thecocktaildb.com/api/json/v1/1/"
    protected open val connectionTimeout: Long = 20
    protected open val readTimeout: Long = 20

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient,
                        jsonConverter: Converter.Factory): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(jsonConverter)
            .build()
    }


    @Provides
    fun provideHttpClient(loggingInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    fun provideMoshiConverter(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()


    @Provides
    fun provideJsonConverterFactory(moshiConverter: Moshi): Converter.Factory = MoshiConverterFactory.create(moshiConverter)


    @Provides
    fun providesCocktailsApi(retrofit: Retrofit): CocktailApi = retrofit.create(CocktailApi::class.java)


}