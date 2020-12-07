package com.kireaji.daggerhiltsample.data.api

import java.lang.IllegalStateException

class GithubClient private constructor(
    private val service: GithubService
) {

    suspend fun userRepos(userName: String): List<RepoResponse> {
        val response = service.userRepos(userName).execute()
        if (!response.isSuccessful) {
            throw IllegalStateException("error")
        }
        val body = response.body() ?: throw IllegalStateException("body is null.")
        return body
    }

    companion object {
        @Volatile
        private var instance: GithubClient? = null

        fun getInstance(service: GithubService): GithubClient {
            return instance ?: GithubClient(service).also {
                instance = it
            }
        }
    }
}