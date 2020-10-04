package alfianyusufabdullah.exp.discussion.data.response

data class DiscussionItemResponse(
    val createdAt: Long? = null,
    val name: String? = null,
    val comment: String? = null,
    val id: String? = null,
    val title: String? = null,
    val reply: Int? = null,
    val isChild: Boolean = false,
    val parentId: String? = null
)