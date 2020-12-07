package com.kireaji.daggerhiltsample.data.repository

import com.kireaji.daggerhiltsample.data.model.GithubRepo

interface GithubRepository {
    suspend fun searchRepos(repositoryName: String): List<GithubRepo>
}