package ru.vsibi.presentation.screens.search.travallers

import androidx.lifecycle.MutableLiveData
import ru.vsibi.presentation.base.BaseViewModel

class TravellersViewModel : BaseViewModel<TravellersViewState, TravellersAction, TravellersEvent>() {

    val adultsCountLiveData: MutableLiveData<Int> = MutableLiveData()
    val childsCountLiveData: MutableLiveData<Int> = MutableLiveData()
    private var adultsCount = 0
    private var childsCount = 0

    override fun obtainEvent(viewEvent: TravellersEvent) {

    }

    fun minusAdult() {
        if (adultsCount > 0) {
            adultsCount--
        }
        adultsCountLiveData.postValue(adultsCount)
    }

    fun plusAdult() {
        adultsCount++
        adultsCountLiveData.postValue(adultsCount)
    }

    fun minusChild() {
        if (childsCount > 0) {
            childsCount--
        }
        childsCountLiveData.postValue(childsCount)
    }

    fun plusChild() {
        childsCount++
        childsCountLiveData.postValue(childsCount)
    }

}