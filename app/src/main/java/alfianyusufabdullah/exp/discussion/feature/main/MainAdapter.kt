package alfianyusufabdullah.exp.discussion.feature.main

import alfianyusufabdullah.exp.discussion.R
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_discussion.view.*

class MainAdapter(
    private val discussions: MutableList<Discussion>,
    private val click: (Discussion) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(discussion: Discussion): View {
            itemView.itemTitleDiscussion.text = discussion.title
            itemView.itemUserDiscussion.text = discussion.name
            itemView.itemQuestionDiscussion.text = discussion.comment?.replace("\n", " ")
            itemView.itemReplyDiscussion.text = discussion.reply.toString()

            val time = "Diposting ${discussion.createdAt}"

            itemView.itemTimeDiscussion.text = time

            if (discussion.reply ?: 0 > 0) {
                itemView.itemPrioritiesDiscussion.visibility = View.GONE
            } else {
                itemView.itemPrioritiesDiscussion.visibility = View.VISIBLE
            }

            return itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(R.layout.item_discussion, parent, false)
            .run(::MainHolder)

    override fun onBindViewHolder(holder: MainHolder, position: Int) =
        holder.bind(discussions[position]).setOnClickListener { click(discussions[position]) }


    override fun getItemCount() = discussions.size

    fun submitList(newDiscussions: List<Discussion>) {
        this.discussions.clear()
        this.discussions.addAll(newDiscussions)
        notifyDataSetChanged()
    }
}