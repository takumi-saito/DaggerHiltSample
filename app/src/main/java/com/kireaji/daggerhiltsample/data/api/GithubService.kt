package com.kireaji.daggerhiltsample.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("users/{user}/repos")
    fun userRepos(
        @Path("user") user: String,
        @Query("sort") sort: String = "desc"
    ): Call<List<RepoResponse>>
}