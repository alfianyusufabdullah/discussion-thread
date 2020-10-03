package alfianyusufabdullah.exp.discussion.data.route

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DiscussionService {

    @FormUrlEncoded
    @POST("discussion/publish")
    suspend fun publishNewDiscussionOrComment(
        @Field("type") type: String,
        @Field("title") title: String = "-",
        @Field("comment") comment: String,
        @Field("name") name: String
    )

    @GET("discussion/")
    suspend fun findAllDiscussionWithParent(
        @Field("parentId") parentId: Int? = null
    )
}