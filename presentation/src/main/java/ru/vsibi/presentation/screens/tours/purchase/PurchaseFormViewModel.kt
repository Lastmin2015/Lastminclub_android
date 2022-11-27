package ru.vsibi.presentation.screens.tours.purchase

import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.models.PersonalDataModel
import ru.vsibi.presentation.screens.tours.main.TourModel

class PurchaseFormViewModel : BaseViewModel<PurchaseFormState, PurchaseFormAction, PurchaseFormEvent>() {

    private var hotelsModel: TourModel? = null
    private var persons = mutableListOf<PersonalDataModel>()
    init {
        persons.add(PersonalDataModel(1))
        persons.add(PersonalDataModel(2))
    }
    override fun obtainEvent(viewEvent: PurchaseFormEvent) {
        when (viewEvent) {
            is PurchaseFormEvent.ConfigureArgs -> {
                configureArgs(viewEvent.data)
            }
            is PurchaseFormEvent.UpdatePerson -> {
                updatePerson(viewEvent.person)
            }
        }
    }

    private fun updatePerson(person: PersonalDataModel?) {
        if(person == null) return
        if(person.id == 1){
            viewAction = PurchaseFormAction.AdultUpdated(person)
            persons[0] = person
        }
        if(person.id == 2){
            viewAction = PurchaseFormAction.ChildUpdated(person)
            persons[1] = person
        }
    }

    private fun configureArgs(data: TourModel) {
        this.hotelsModel = data
        viewState = PurchaseFormState.Loaded(data)
    }

    fun getChild(): PersonalDataModel {
        return persons[1]
    }

    fun getAdult(): PersonalDataModel {
        return persons[0]
    }
}