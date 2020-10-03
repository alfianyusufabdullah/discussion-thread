package alfianyusufabdullah.exp.discussion.data

import alfianyusufabdullah.exp.discussion.data.response.DiscussionResponse
import alfianyusufabdullah.exp.discussion.data.route.DiscussionService
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DiscussionRepository(private val discussionService: DiscussionService) {

    suspend fun publishNewDiscussion(discussion: Discussion): Flow<DiscussionResponseOutput<DiscussionResponse>> {
        return flow {
            val response = discussionService.publishNewDiscussionOrComment(
                "discussion",
                discussion.title,
                discussion.comment,
                discussion.name
            )

            if (response.isSuccessful){
                emit(DiscussionResponseOutput.Success(response.body()))
            } else {
                emit(DiscussionResponseOutput.Failure(IOException("")))
            }

        }
    }

    suspend fun publishNewComment(discussion: Discussion): Flow<String> {

    }

    suspend fun findAllDiscussion() {

    }

    suspend fun findAllDiscussionWithParentId(parentId: Int) {

    }
}