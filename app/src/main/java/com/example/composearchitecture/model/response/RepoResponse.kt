package com.example.composearchitecture.model.response

data class RepoResponse(
    val id: Long,
    val name: String,
    val htmlUrl: String,
    val url: String,
    val gitUrl: String
)