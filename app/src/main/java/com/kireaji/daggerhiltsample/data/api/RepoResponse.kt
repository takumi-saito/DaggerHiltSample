package com.kireaji.daggerhiltsample.data.api

import com.kireaji.daggerhiltsample.data.model.GithubRepo
import com.squareup.moshi.Json

data class RepoResponse(
    @field:Json(name = "name")
    val repoName: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "language")
    val language: String?,
    @field:Json(name = "url")
    val repoUrl: String?
) {
    companion object {
        fun toModel(repoResponse: RepoResponse): GithubRepo {
            return GithubRepo(
                repoName = repoResponse.repoName ?: "",
                description = repoResponse.description ?: "",
                repoUrl = repoResponse.repoUrl ?: "",
                language = repoResponse.language ?: "",
            )
        }
    }
}

fun RepoResponse.toModel() = RepoResponse.toModel(this)