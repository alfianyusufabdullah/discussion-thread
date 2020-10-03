package alfianyusufabdullah.exp.discussion.domain.model


data class Discussion(
    val createdAt: Long,
    val name: String,
    val comment: String,
    val id: Int,
    val title: String,
    val reply: Int,
    val parentId: Int
)