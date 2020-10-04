package alfianyusufabdullah.exp.discussion.feature.thread

import alfianyusufabdullah.exp.discussion.R
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.thread_child_item.view.*
import kotlinx.android.synthetic.main.thread_item.view.*

class ThreadAdapter(
    private val discussions: MutableList<Discussion>,
    private val click: (Discussion) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ThreadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(discussion: Discussion): View {
            itemView.itemUserThread.text = discussion.name
            itemView.itemQuestionThread.text = discussion.comment
            itemView.itemTimeThread.text = discussion.createdAt.toString()

            return itemView
        }
    }

    class ThreadHolderChild(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(discussion: Discussion, nextDiscussion: Discussion?): View {
            itemView.itemUserChild.text = discussion.name
            itemView.itemQuestionChild.text = discussion.comment

            val time = "Diposting ${discussion.createdAt}"

            itemView.itemTimeChild.text = time

            nextDiscussion?.let {
                if (!it.isChild) {
                    itemView.timeline.visibility = View.GONE
                }
            }

            return itemView
        }
    }

    override fun getItemViewType(position: Int): Int {
        println("Discussion isChild " + discussions[position].isChild)
        return if (discussions[position].isChild) {
            THREAD_CHILD_ITEM
        } else {
            THREAD_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        THREAD_ITEM -> LayoutInflater.from(parent.context)
            .inflate(R.layout.thread_item, parent, false)
            .run(::ThreadHolder)
        THREAD_CHILD_ITEM -> {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.thread_child_item, parent, false)
                .run(::ThreadHolderChild)
        }

        else -> throw IllegalArgumentException("Unknown viewType")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ThreadHolder -> holder.bind(discussions[position])
                .setOnClickListener { click(discussions[position]) }
            is ThreadHolderChild -> {
                val discussion: Discussion? = try {
                    discussions[position + 1]
                } catch (e: Exception) {
                    null
                }

                holder.bind(discussions[position], discussion)
                    .setOnClickListener { click(discussions[position]) }
            }
        }
    }


    override fun getItemCount() = discussions.size

    fun submitList(newDiscussions: List<Discussion>) {
        this.discussions.clear()
        this.discussions.addAll(newDiscussions)
        notifyDataSetChanged()
    }

    companion object {
        private const val THREAD_ITEM = 1
        private const val THREAD_CHILD_ITEM = 2
    }

}