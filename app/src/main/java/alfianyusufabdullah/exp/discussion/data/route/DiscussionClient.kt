package alfianyusufabdullah.exp.discussion.data.route

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscussionClient {

    val clientService: DiscussionClient
        get() {
            return Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DiscussionClient::class.java)
        }
}