package com.app.startupscreenjetpack.network

import com.app.startupscreenjetpack.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val builder =  Retrofit.Builder()
            .baseUrl("https://6471a6946a9370d5a41a84bb.mockapi.io/")
            //.client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.build()

        val client = OkHttpClient.Builder()

        client.connectTimeout(160, TimeUnit.SECONDS)
        client.readTimeout(160, TimeUnit.SECONDS)
        client.writeTimeout(160, TimeUnit.SECONDS)
        // add logging interceptor if DEBUG build
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            //loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(loggingInterceptor)
        }

        client.interceptors().add(Interceptor { chain ->
            val newRequestBuilder = chain.request().newBuilder()
            // .addHeader("Content-Type", "application/json")
            //.addHeader("Connection", "keep-alive")
            //.addHeader("app-version", BuildConfig.VERSION_NAME.toString())
            ///.addHeader("device-type", AppPreference.DEVICE_TYPE)
            //.addHeader("device-model", android.os.Build.DEVICE+" "+android.os.Build.MODEL)


            /*
                            if(AppPreference.USER_DATA == null) {
                                        newRequestBuilder.addHeader("fcm-token", AppPreference.FCM_TOKEN)
                                        newRequestBuilder.addHeader("unique-id", android.os.Build.SERIAL)
                                    }
            */

            /*
                            if (AppPreference.ACCESS_TOKEN.isNotBlank()) {
                                newRequestBuilder.addHeader("Authorization", "Bearer"+" "+ AppPreference.ACCESS_TOKEN)

                            }
            */


            chain.proceed(newRequestBuilder.build())
        })

        builder.client(client.build())
        return builder.build()


    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}

/*
object ApiClient {


    private val client: Retrofit

        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val builder = Retrofit.Builder()
                .baseUrl("BuildConfig.DOMAIN_URL")
                .addConverterFactory(GsonConverterFactory.create(gson))

            val client = OkHttpClient.Builder()

            client.connectTimeout(160, TimeUnit.SECONDS)
            client.readTimeout(160, TimeUnit.SECONDS)
            client.writeTimeout(160, TimeUnit.SECONDS)
            // add logging interceptor if DEBUG build
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
              //loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(loggingInterceptor)
            }

            client.interceptors().add(Interceptor { chain ->
                val newRequestBuilder = chain.request().newBuilder()
                   // .addHeader("Content-Type", "application/json")
                    //.addHeader("Connection", "keep-alive")
                    //.addHeader("app-version", BuildConfig.VERSION_NAME.toString())
                    ///.addHeader("device-type", AppPreference.DEVICE_TYPE)
                    //.addHeader("device-model", android.os.Build.DEVICE+" "+android.os.Build.MODEL)


*/
/*
                if(AppPreference.USER_DATA == null) {
                            newRequestBuilder.addHeader("fcm-token", AppPreference.FCM_TOKEN)
                            newRequestBuilder.addHeader("unique-id", android.os.Build.SERIAL)
                        }
*//*


*/
/*
                if (AppPreference.ACCESS_TOKEN.isNotBlank()) {
                    newRequestBuilder.addHeader("Authorization", "Bearer"+" "+ AppPreference.ACCESS_TOKEN)

                }
*//*



                chain.proceed(newRequestBuilder.build())
            })

            builder.client(client.build())

            return builder.build()
        }


    val service: ApiService
        get() = client.create(ApiService::class.java)


}*/
