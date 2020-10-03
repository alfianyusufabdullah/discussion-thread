package alfianyusufabdullah.exp.discussion.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> = errorLiveData

    protected val loadingLiveData = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = loadingLiveData

    init {
        loadingLiveData.postValue(false)
    }
}