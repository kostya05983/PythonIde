package org.jetspirit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GitHubApi {

    @POST("/login/oauth/access_token?{client_id}&{client_secret}&{code}")
    fun getAccessToken(@Path("client_id") clientId: String, @Path("client_secret") clientSecret: String,
                       @Path("code") code: String)

    @GET("/login/oauth/authorize?{client_id}&{code}")
    fun authorize(@Path("client_id") clientId: String, @Path("code") code: String)

    companion object {
        fun getApi(): GitHubApi {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://github.com/")
                    .build()
            return retrofit.create(GitHubApi::class.java)
        }
    }
}