package alfianyusufabdullah.exp.discussion.feature.main

import alfianyusufabdullah.exp.discussion.R
import alfianyusufabdullah.exp.discussion.data.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val mainAdapter: MainAdapter by lazy { MainAdapter(mutableListOf()) }
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        discussions.run {
            hasFixedSize()
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        mainViewModel.findAllDiscussion()
        mainViewModel.discussions.observe(this) {
            when (it) {
                is Resources.Success -> mainAdapter.submitList(it.data ?: listOf())
            }
        }
    }
}