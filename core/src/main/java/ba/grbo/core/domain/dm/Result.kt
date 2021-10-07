package ba.grbo.core.domain.dm

sealed class Result<out R> {
    sealed class SourceResult<out R>: Result<R>() {
        data class Success<out T>(val data: T) : SourceResult<T>()
        data class Failure(val exception: Exception) : SourceResult<Nothing>()
    }

    data class Loading(val loading: Boolean) : Result<Nothing>()

    override fun toString() = when (this) {
        is SourceResult.Success<*> -> "Success[data=$data]"
        is SourceResult.Failure -> "Failure[exception=$exception]"
        is Loading -> "Loading"
    }
}