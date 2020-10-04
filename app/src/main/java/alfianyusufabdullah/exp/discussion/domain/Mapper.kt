package alfianyusufabdullah.exp.discussion.domain

import alfianyusufabdullah.exp.discussion.data.response.DiscussionItemResponse
import alfianyusufabdullah.exp.discussion.domain.model.Discussion

class Mapper {

    fun discussionDataToDomain(discussionItemResponse: DiscussionItemResponse) =
        Discussion(
            id = discussionItemResponse.id,
            parentId = discussionItemResponse.parentId,
            title = discussionItemResponse.title,
            comment = discussionItemResponse.comment,
            name = discussionItemResponse.name,
            reply = discussionItemResponse.reply,
            isChild = discussionItemResponse.isChild,
            createdAt = discussionItemResponse.createdAt
        )
}