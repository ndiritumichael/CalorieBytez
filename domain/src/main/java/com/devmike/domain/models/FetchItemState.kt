package com.devmike.domain.models

sealed interface FetchItemState<out T> {

    data object Loading : FetchItemState<Nothing>
    data object Idle : FetchItemState<Nothing>

    data class Error(val err: AppErrors) : FetchItemState<Nothing>

    data class Success<T>(val data: T) : FetchItemState<T>
}
