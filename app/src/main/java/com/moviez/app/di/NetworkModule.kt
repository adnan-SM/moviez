package com.moviez.app.di

import androidx.annotation.NonNull
import com.moviez.app.BuildConfig
import com.moviez.app.api.LocalDataSource
import com.moviez.app.api.RemoteDataSource
import com.moviez.app.api.retrofit.RequestInterceptor
import com.moviez.app.api.retrofit.MoviezApiService
import com.moviez.app.db.MovieDao
import com.moviez.app.db.PageDao
import com.moviez.app.repository.MovieRepository
import com.moviez.app.repository.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author adnan
 * @since  09/06/20
 */
@Module
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(@NonNull okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviezApiService(@NonNull retrofit: Retrofit): MoviezApiService {
        return retrofit.create(MoviezApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(moviezApiService: MoviezApiService)
            = RemoteDataSource(moviezApiService)

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao, pageDao: PageDao)
            = LocalDataSource(movieDao, pageDao)

    @Singleton
    @Provides
    fun provideRepository(localDataSource: LocalDataSource,
                          remoteDataSource: RemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDataSource)
    }


}