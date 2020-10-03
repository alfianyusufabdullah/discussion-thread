package alfianyusufabdullah.exp.discussion.data

import alfianyusufabdullah.exp.discussion.data.response.DiscussionResponse
import alfianyusufabdullah.exp.discussion.data.route.DiscussionService
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DiscussionRepository(private val discussionService: DiscussionService) {

    suspend fun publishNewDiscussion(discussion: Discussion): Flow<Resources<DiscussionResponse>> {
        return flow {
            val response = discussionService.publishNewDiscussionOrComment(
                "discussion",
                discussion.title,
                discussion.comment,
                discussion.name
            )

            if (response.isSuccessful) {
                emit(Resources.Success(response.body()))
            } else {
                emit(Resources.Failure(IOException(response.message())))
            }
        }
    }

    suspend fun publishNewComment(discussion: Discussion): Flow<Resources<DiscussionResponse>> {
        return flow {
            val response = discussionService.publishNewDiscussionOrComment(
                type = "comment",
                comment = discussion.comment,
                name = discussion.name
            )

            if (response.isSuccessful) {
                emit(Resources.Success(response.body()))
            } else {
                emit(Resources.Failure(IOException(response.message())))
            }
        }
    }

    suspend fun findAllDiscussion(): Flow<Resources<DiscussionResponse>> {
        return flow {
            val response = discussionService.findAllDiscussionWithParent()

            if (response.isSuccessful) {
                emit(Resources.Success(response.body()))
            } else {
                emit(Resources.Failure(IOException(response.message())))
            }
        }
    }

    suspend fun findAllDiscussionWithParentId(parentId: Int): Flow<Resources<DiscussionResponse>> {
        return flow {
            val response = discussionService.findAllDiscussionWithParent(parentId)

            if (response.isSuccessful) {
                emit(Resources.Success(response.body()))
            } else {
                emit(Resources.Failure(IOException(response.message())))
            }
        }
    }
}