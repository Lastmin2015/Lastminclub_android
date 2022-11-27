package ru.vsibi.presentation.screens.tours.info.flights.info

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vsibi.presentation.screens.tours.info.flights.info.models.FlightsInfoModel

class FlightsInfoViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private val flights: List<FlightsInfoModel>) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount() = flights.size

    override fun createFragment(position: Int) =
        flights[position].fragment/*.apply {
            arguments = Bundle().apply { putParcelable(KEY_FLIGHTS, flights[position].flight) }
        }*/
}