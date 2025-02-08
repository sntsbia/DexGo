package com.sntsb.dexgo.pokemon.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sntsb.dexgo.pokemon.dto.PokemonDTO

class PokemonByTypePagingSource(
    private val itemList: List<PokemonDTO>
) : PagingSource<Int, PokemonDTO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDTO> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val fromIndex = (page - 1) * pageSize
        val toIndex = minOf(fromIndex + pageSize, itemList.size)

        return try {
            val items = itemList.subList(fromIndex, toIndex)

            val nextKey = if (toIndex < itemList.size) page + 1 else null

            LoadResult.Page(
                data = items, prevKey = if (page == 1) null else page - 1, nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDTO>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}