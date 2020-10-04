package alfianyusufabdullah.exp.discussion.feature.thread

import alfianyusufabdullah.exp.discussion.R
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.thread_item.view.*

class ThreadAdapter(
    private val discussions: MutableList<Discussion>,
    private val click: (Discussion) -> Unit
) :
    RecyclerView.Adapter<ThreadAdapter.ThreadHolder>() {

    class ThreadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(discussion: Discussion): View {
            itemView.itemUserThread.text = discussion.name
            itemView.itemQuestionThread.text = discussion.comment
            itemView.itemTimeThread.text = discussion.createdAt.toString()
            itemView.itemReplyThread.text = discussion.reply.toString()

            return itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(R.layout.thread_item, parent, false)
            .run(::ThreadHolder)

    override fun onBindViewHolder(holder: ThreadHolder, position: Int) =
        holder.bind(discussions[position]).setOnClickListener { click(discussions[position]) }

    override fun getItemCount() = discussions.size

    fun submitList(newDiscussions: List<Discussion>) {
        this.discussions.clear()
        this.discussions.addAll(newDiscussions)
        notifyDataSetChanged()
    }
}