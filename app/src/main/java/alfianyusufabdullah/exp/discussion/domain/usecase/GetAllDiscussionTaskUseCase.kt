package alfianyusufabdullah.exp.discussion.domain.usecase

import alfianyusufabdullah.exp.discussion.data.DiscussionRepository
import alfianyusufabdullah.exp.discussion.data.Resources
import alfianyusufabdullah.exp.discussion.domain.Mapper
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetAllDiscussionTaskUseCase(
    private val repository: DiscussionRepository, private val mapper: Mapper
) {

    suspend fun findAllDiscussion(): Flow<Resources<List<Discussion>>> {
        return flow {
            when (val result = repository.findAllDiscussion().first()) {
                is Resources.Success -> {
                    val discussions = result.data?.discussion?.map {
                        mapper.discussionDataToDomain(it)
                    }
                    emit(Resources.Success(discussions))
                }
                is Resources.Failure -> {
                    result.exception.printStackTrace()
                    emit(Resources.Failure(result.exception))
                }
            }
        }
    }
}