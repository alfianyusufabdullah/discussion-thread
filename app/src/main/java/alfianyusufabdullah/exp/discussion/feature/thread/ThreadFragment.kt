package alfianyusufabdullah.exp.discussion.feature.thread

import alfianyusufabdullah.exp.discussion.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class ThreadFragment : Fragment() {

    companion object {
        fun newInstance() = ThreadFragment()
    }

    private lateinit var viewModel: ThreadViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.thread_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ThreadViewModel::class.java)
        // TODO: Use the ViewModel
    }

}