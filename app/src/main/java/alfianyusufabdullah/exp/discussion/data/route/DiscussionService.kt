package alfianyusufabdullah.exp.discussion.data.route

import alfianyusufabdullah.exp.discussion.data.response.DiscussionResponse
import retrofit2.Response
import retrofit2.http.*

interface DiscussionService {

    @FormUrlEncoded
    @POST("discussion/publish")
    suspend fun publishNewDiscussionOrComment(
        @Field("type") type: String,
        @Field("title") title: String = "-",
        @Field("comment") comment: String,
        @Field("name") name: String
    ): Response<DiscussionResponse>

    @GET("discussion/")
    suspend fun findAllDiscussionWithParent(
        @Query("parentId") parentId: String? = null
    ): Response<DiscussionResponse>
}