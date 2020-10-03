package alfianyusufabdullah.exp.discussion.domain.model


data class Discussion(
    val createdAt: Long? = 0,
    val name: String? = "-",
    val comment: String? = "-",
    val id: Int? = 0,
    val title: String? = "-",
    val reply: Int? = 0,
    val parentId: Int? = 0
)