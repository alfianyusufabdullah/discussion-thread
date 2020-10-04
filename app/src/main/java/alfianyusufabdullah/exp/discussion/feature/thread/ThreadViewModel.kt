package alfianyusufabdullah.exp.discussion.feature.thread

import alfianyusufabdullah.exp.discussion.data.Resources
import alfianyusufabdullah.exp.discussion.domain.model.Discussion
import alfianyusufabdullah.exp.discussion.domain.usecase.GetAllDiscussionWithParentIdTaskUseCase
import alfianyusufabdullah.exp.discussion.feature.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ThreadViewModel(
    private val allDiscussionWithParentIdTaskUseCase: GetAllDiscussionWithParentIdTaskUseCase
) : BaseViewModel() {

    private val _discussions = MutableLiveData<Resources<List<Discussion>>>()
    val discussions: LiveData<Resources<List<Discussion>>> = _discussions

    fun findAllDiscussionWithParentId(parentId: String) {
        viewModelScope.launch {
            allDiscussionWithParentIdTaskUseCase.findAllDiscussionWithId(parentId)
                .onStart { loadingLiveData.postValue(true) }
                .onCompletion { loadingLiveData.postValue(false) }
                .catch { errorLiveData.postValue(it.message) }
                .collect { _discussions.postValue(it) }
        }
    }
}