package alfianyusufabdullah.exp.discussion.feature.thread

import alfianyusufabdullah.exp.discussion.R
import alfianyusufabdullah.exp.discussion.data.Resources
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.thread_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class ThreadFragment : Fragment() {

    private val threadViewModel: ThreadViewModel by viewModel()
    private val discussion: Discussion by lazy { arguments?.getParcelable<Discussion>(DISCUSSION_KEY) as Discussion }
    private val threadAdapter: ThreadAdapter by lazy { ThreadAdapter(mutableListOf(), click) }

    private val click: (Discussion) -> Unit = {
        requireActivity().supportFragmentManager.beginTransaction()
            .add(android.R.id.content, newInstance(it))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        private const val DISCUSSION_KEY = "discussion"
        fun newInstance(discussion: Discussion) = ThreadFragment().apply {
            val bundle = Bundle().apply {
                putParcelable(DISCUSSION_KEY, discussion)
            }

            arguments = bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.fade)
        enterTransition = inflater.inflateTransition(android.R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thread_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        threadToolbar.setNavigationIcon(R.drawable.ic_back)
        threadToolbar.title = "Thread"
        threadToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        renderParent(discussion)

        threads.run {
            hasFixedSize()
            layoutManager = LinearLayoutManager(requireContext())
            adapter = threadAdapter
        }

        threadViewModel.findAllDiscussionWithParentId(discussion.id ?: "1")
        threadViewModel.discussions.observe(viewLifecycleOwner) {
            when (it) {
                is Resources.Success -> threadAdapter.submitList(it.data ?: listOf())
            }
        }
    }

    private fun renderParent(discussion: Discussion) {
        if (discussion.title.isNullOrBlank()) {
            itemTitleDiscussion.visibility = View.GONE
        } else {
            itemTitleDiscussion.text = discussion.title
        }

        itemUserDiscussion.text = discussion.name
        itemTimeDiscussion.text = discussion.createdAt.toString()
        itemQuestionDiscussion.text = discussion.comment
    }
}