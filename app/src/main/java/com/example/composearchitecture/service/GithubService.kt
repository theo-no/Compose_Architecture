package com.example.composearchitecture.service

import com.example.composearchitecture.model.response.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun getRepos(@Path("user") user:String): List<RepoResponse>
}