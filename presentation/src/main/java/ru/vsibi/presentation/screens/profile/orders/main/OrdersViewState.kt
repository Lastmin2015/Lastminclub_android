package ru.vsibi.presentation.screens.profile.orders.main

import ru.vsibi.presentation.screens.tours.main.TourModel

sealed class OrdersViewState {
    class Loaded(val data : List<TourModel>) : OrdersViewState()
}
