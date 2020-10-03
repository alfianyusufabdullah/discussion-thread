package alfianyusufabdullah.exp.discussion.domain.usecase

import alfianyusufabdullah.exp.discussion.data.DiscussionRepository
import alfianyusufabdullah.exp.discussion.data.Resources
import alfianyusufabdullah.exp.discussion.domain.Mapper
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetAllDiscussionTaskUseCase(
    private val repository: DiscussionRepository, private val mapper: Mapper
) {

    suspend fun findAllDiscussion(): Flow<List<Discussion>?> {
        return flow {
            when (val result = repository.findAllDiscussion().first()) {
                is Resources.Success -> {
                    val discussions = result.data?.discussion?.map {
                        mapper.discussionDataToDomain(it)
                    }
                    emit(discussions)
                }
                is Resources.Failure -> {
                    throw IOException(result.exception)
                }
            }
        }
    }
}