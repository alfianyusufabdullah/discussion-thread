package alfianyusufabdullah.exp.discussion.data

import alfianyusufabdullah.exp.discussion.data.response.DiscussionResponse
import alfianyusufabdullah.exp.discussion.data.route.DiscussionService
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

class DiscussionRepository(private val discussionService: DiscussionService) {

    suspend fun publishNewDiscussion(discussion: Discussion): Flow<Resources<DiscussionResponse>> {
        return invokeApiCall {
            discussionService.publishNewDiscussionOrComment(
                "discussion",
                discussion.title ?: "-",
                discussion.comment ?: "-",
                discussion.name ?: "-"
            )
        }
    }

    suspend fun publishNewComment(discussion: Discussion): Flow<Resources<DiscussionResponse>> {
        return invokeApiCall {
            discussionService.publishNewDiscussionOrComment(
                type = "comment",
                comment = discussion.comment ?: "-",
                name = discussion.name ?: "-"
            )
        }
    }

    suspend fun findAllDiscussion(): Flow<Resources<DiscussionResponse>> {
        return invokeApiCall {
            discussionService.findAllDiscussionWithParent()
        }
    }

    suspend fun findAllDiscussionWithParentId(parentId: String): Flow<Resources<DiscussionResponse>> {
        return invokeApiCall {
            discussionService.findAllDiscussionWithParent(parentId)
        }
    }

    private fun invokeApiCall(call: suspend () -> Response<DiscussionResponse>): Flow<Resources<DiscussionResponse>> {
        return flow {
            try {
                val response = call.invoke()
                if (response.isSuccessful) {
                    emit(Resources.Success(response.body()))
                } else {
                    println(response.message())
                    emit(Resources.Failure(IOException("Failure derived data [${response.message()}]")))
                }
            } catch (e: Exception) {
                println(e.message)
                emit(Resources.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}