package ru.vsibi.presentation.base

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.movemove.presentation.helpers.SingleLiveAction

abstract class BaseViewModel<State, Action, Event> : ViewModel() {

    val TAG = this.javaClass.simpleName
    private val _viewStates: MutableLiveData<State> = MutableLiveData()
    fun viewStates(): LiveData<State> = _viewStates

    private var _viewState: State? = null
    protected var viewState: State
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            _viewState = value
            _viewStates.postValue(value)
        }

    private val _viewActions: SingleLiveAction<Action> = SingleLiveAction()
    fun viewActions(): SingleLiveAction<Action> = _viewActions

    private var _viewAction: Action? = null
    protected var viewAction: Action
        get() = _viewAction
            ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            _viewAction = value
            _viewActions.postValue(value)
        }

    abstract fun obtainEvent(viewEvent: Event)

    fun CoroutineScope.launchOnIO(unit: suspend () -> Unit) {
        this.launch(Dispatchers.IO) {
            unit.invoke()
        }
    }
//    inline fun <T> launchOnViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<State> {
//        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
//            emitSource(block())
//        }
//    }
}