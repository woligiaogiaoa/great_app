package com.jsn.great.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

@ActivityRetainedScoped
class ArticleRepository @Inject constructor(val retrofit: Retrofit){

    val articleService by lazy { retrofit.create(ArticleService::class.java) }

    suspend fun getArticles(page: String): BaseData<ArticleListEntity> {
        return  articleService.listArticles(page)
    }
}

interface ArticleService {
    @GET("article/list/{page}/json") //https://www.wanandroid.com/article/list/1/json
    suspend fun listArticles(@Path("page") page: String): BaseData<ArticleListEntity>
}




@Module
@InstallIn(ApplicationComponent::class)
object AnalyticsModule {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient()=OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()
}

