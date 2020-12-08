package com.kireaji.daggerhiltsample.data.api

import java.lang.IllegalStateException
import javax.inject.Inject

class GithubClient @Inject constructor(
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
}