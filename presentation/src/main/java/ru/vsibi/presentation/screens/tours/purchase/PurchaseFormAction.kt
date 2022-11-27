package ru.vsibi.presentation.screens.tours.purchase

import ru.vsibi.presentation.models.PersonalDataModel

sealed class PurchaseFormAction {
    class AdultUpdated(val person : PersonalDataModel) : PurchaseFormAction()
    class ChildUpdated(val person : PersonalDataModel) : PurchaseFormAction()
}
