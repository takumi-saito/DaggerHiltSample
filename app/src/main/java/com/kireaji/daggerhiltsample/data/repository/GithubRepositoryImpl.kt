package com.kireaji.daggerhiltsample.data.repository

import com.kireaji.daggerhiltsample.data.api.GithubClient
import com.kireaji.daggerhiltsample.data.api.toModel
import com.kireaji.daggerhiltsample.data.model.GithubRepo
import javax.inject.Inject


class GithubRepositoryImpl @Inject constructor(
    private val client: GithubClient
) : GithubRepository {
    override suspend fun searchRepos(userName: String): List<GithubRepo> {
        return client.userRepos(userName)
            .map {
                it.toModel()
            }
    }
}