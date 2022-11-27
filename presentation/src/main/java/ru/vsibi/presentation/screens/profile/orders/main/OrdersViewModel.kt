package ru.vsibi.presentation.screens.profile.orders.main

import ru.vsibi.presentation.base.BaseViewModel
import ru.vsibi.presentation.screens.tours.main.HotelsMockFactory

class OrdersViewModel : BaseViewModel<OrdersViewState, OrdersAction, OrdersEvent>() {

    override fun obtainEvent(viewEvent: OrdersEvent) {
        when(viewEvent){
            is OrdersEvent.Default -> fetchHotels()
        }
    }

    private fun fetchHotels() {
        viewState = OrdersViewState.Loaded(HotelsMockFactory.getMockHotels())
    }

}