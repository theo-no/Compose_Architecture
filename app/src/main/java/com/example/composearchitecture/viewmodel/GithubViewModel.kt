package com.example.composearchitecture.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composearchitecture.model.response.RepoResponse
import com.example.composearchitecture.service.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val githubService: GithubService
) : ViewModel() {
    val repos = mutableStateListOf<RepoResponse>()

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = githubService.getRepos("theo-no")
            repos.addAll(result)
        }
    }
}