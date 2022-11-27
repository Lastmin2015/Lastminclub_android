package ru.vsibi.presentation.screens.search.main

object SearchFactory {

    fun getCountryList() : List<String>{
        val list = ArrayList<String>()
        list.add("Turkey")
        list.add("Greece")
        list.add("Cyprus")
        list.add("Spain")
        list.add("Bulgary")
        list.add("Canary Islands")
        list.add("Croatia")
        return list
    }
}