package alfianyusufabdullah.exp.discussion.data

sealed class Resources<out T> {
    data class Success<out T>(val data: T?) : Resources<T>()
    data class Failure(val exception: Exception) : Resources<Nothing>()
}