package ru.vsibi.presentation.screens.tours.purchase

import ru.vsibi.presentation.screens.tours.main.TourModel

sealed class PurchaseFormState {
    class Loaded(val data : TourModel) : PurchaseFormState()
}