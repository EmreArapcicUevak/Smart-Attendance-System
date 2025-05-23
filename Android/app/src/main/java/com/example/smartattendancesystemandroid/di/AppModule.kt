package com.example.smartattendancesystemandroid.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.smartattendancesystemandroid.auth.AuthApi
import com.example.smartattendancesystemandroid.auth.AuthRepository
import com.example.smartattendancesystemandroid.auth.AuthRepositoryImplementation
import com.example.smartattendancesystemandroid.data.token.TokenProvider
import com.example.smartattendancesystemandroid.data.token.TokenProviderImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://172.16.9.15:8080/") // ip address of the server
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideTokenProvider(prefs: SharedPreferences): TokenProvider {
        return TokenProviderImplementation(prefs = prefs)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, tokenProvider: TokenProvider): AuthRepository {
        return AuthRepositoryImplementation(api, tokenProvider)
    }
}