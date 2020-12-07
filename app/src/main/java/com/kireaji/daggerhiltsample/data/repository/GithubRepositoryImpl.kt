package com.kireaji.daggerhiltsample.data.repository

import com.kireaji.daggerhiltsample.data.api.GithubClient
import com.kireaji.daggerhiltsample.data.api.toModel
import com.kireaji.daggerhiltsample.data.model.GithubRepo


class GithubRepositoryImpl private constructor(
    private val client: GithubClient
) : GithubRepository {
    override suspend fun searchRepos(userName: String): List<GithubRepo> {
        return client.userRepos(userName)
            .map {
                it.toModel()
            }
    }

    companion object {
        @Volatile
        private var instance: GithubRepository? = null

        fun getInstance(
            githubClient: GithubClient
        ): GithubRepository {
            return instance ?: GithubRepositoryImpl(githubClient).also {
                instance = it
            }
        }
    }
}