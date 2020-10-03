package alfianyusufabdullah.exp.discussion.data.route

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiscussionClient {

    val clientService: DiscussionService
        get() {
            return Retrofit.Builder()
                .baseUrl("https://02d5db12e67c.ngrok.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DiscussionService::class.java)
        }
}