package alfianyusufabdullah.exp.discussion.data.response

data class DiscussionResponse(
	val discussion: List<DiscussionItemResponse> = emptyList(),
	val status: String? = null
)