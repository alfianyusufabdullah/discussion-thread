package alfianyusufabdullah.exp.discussion.data

sealed class DiscussionResponseOutput<out T> {
    data class Success<out T>(val data: T?) : DiscussionResponseOutput<T>()
    data class Failure(val exception: Exception) : DiscussionResponseOutput<Nothing>()
}