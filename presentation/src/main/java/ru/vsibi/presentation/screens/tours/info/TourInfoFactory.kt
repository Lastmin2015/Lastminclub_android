package ru.vsibi.presentation.screens.tours.info

object TourInfoFactory {

    fun getBoardingList(): List<TourCellModel> {
        val list = mutableListOf<TourCellModel>()
        list.add(TourCellModel("All Inclusive", "€1190"))
        list.add(TourCellModel("Half Board", "€1070"))
        list.add(TourCellModel("Breakfast", "€1000"))
        list.add(TourCellModel("Room only", "€970"))
        return list
    }

    fun getRoomTypesList(): List<TourCellModel> {
        val list = mutableListOf<TourCellModel>()
        list.add(TourCellModel("Standard Room", "€1190"))
        list.add(TourCellModel("Sea View", "€1070"))
        list.add(TourCellModel("Swim-up Room", "€1000"))
        list.add(TourCellModel("Superior Deluxe", "€970"))
        return list
    }

    fun getTransferList(): List<TourCellModel> {
        val list = mutableListOf<TourCellModel>()
        list.add(TourCellModel("Individual", "€1190"))
        list.add(TourCellModel("All together", "€1070"))
        list.add(TourCellModel("Bus", "€1000"))
        list.add(TourCellModel("Taxi", "€970"))
        return list
    }

}