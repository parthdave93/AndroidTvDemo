package com.parthdave93.di.modules

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.parthdave93.di.NetworkMockInterceptor
import com.parthdave93.tvdemo.di.WebApi
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepository
import com.parthdave93.tvdemo.di.networkrepository.NetworkRepositoryImpl
import com.parthdave93.tvdemo.di.scopes.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * Created by Parth Dave on 29-06-2018.
 */

@Module
open class NetworkModule @Inject constructor(val context: Context) {

    @Provides
    @Singleton
    fun providesDriverWebApi(retrofit : Retrofit): WebApi {
        return retrofit.create(WebApi::class.java)
    }

    @Provides
    @Singleton
    fun retrofitDep( converterFactory: Converter.Factory, callAdapterFactory: CallAdapter.Factory, client : OkHttpClient): Retrofit{
        var retrofit = Retrofit.Builder()
                .baseUrl(WebApi.BASE_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory).build();

        return retrofit
    }

    @Provides
    @Singleton
    fun converterFactory():Converter.Factory{
        var gson = GsonBuilder()
                .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun AdapterFactory():CallAdapter.Factory{
        return CoroutineCallAdapterFactory()
    }


    @Provides
    @Singleton
    fun client(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
      /* var mockInterceptor = Interceptor{
            chain ->
                val request = chain?.request()?.newBuilder()
                if(prefs.getAuthToken()!=null)
                    request?.addHeader("Authorization",prefs.getAuthToken())

                chain?.proceed(request?.build())
       }*/
        return OkHttpClient.Builder()
//                .addInterceptor(mockInterceptor)
                .addInterceptor(NetworkMockInterceptor(context))
                .addNetworkInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesNetworkRepository(api: WebApi): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }
}