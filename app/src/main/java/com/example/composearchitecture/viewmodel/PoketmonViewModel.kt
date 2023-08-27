package com.example.composearchitecture.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.composearchitecture.model.response.PoketmonResponse
import com.example.composearchitecture.model.response.Response
import com.example.composearchitecture.service.PoketmonService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PoketmonViewModel @Inject constructor(
    private val poketmonService: PoketmonService
) : ViewModel(){


    val pokemonList: Flow<PagingData<Response.Result>> = getPokemons().cachedIn(viewModelScope)
    var pokemonResult by mutableStateOf(
        PoketmonResponse(
            PoketmonResponse.Species(""),
            PoketmonResponse.Sprites("")
        )
    )

    private fun getPokemons(): Flow<PagingData<Response.Result>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            prefetchDistance = 5
        ),
        pagingSourceFactory = {
            object : PagingSource<Int, Response.Result>() {
                override fun getRefreshKey(state: PagingState<Int, Response.Result>): Int? {
                    return state.anchorPosition
                }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response.Result> {
                    try {
                        val poketmons = if (params.key != null) {
                            poketmonService.getPokemons(params.key as Int, params.loadSize)
                        } else {
                            poketmonService.getPokemons()
                        }
                        // 단계 2: `offset=20&limit=20` 형태의 주소에서
                        // `prevKey`와 `nextKey`를 만들어 전달하자.
                        return LoadResult.Page(
                            data = poketmons.results,
                            prevKey = poketmons.previous?.substringAfter("offset=")?.substringBefore("&")?.toInt(),
                            nextKey = poketmons.next?.substringAfter("offset=")?.substringBefore("&")?.toInt()
                        )
                    } catch (e: Exception) {
                        Log.e("EEE", "error: $e")
                        e.printStackTrace()
                        return LoadResult.Error(e)
                    }
                }
            }
        }
    ).flow

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            pokemonResult = poketmonService.getPokemon(pokemonId)
        }
    }
}