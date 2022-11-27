package ru.vsibi.presentation.screens.tours.info.flights.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentFligthsInfoChildBinding
import ru.vsibi.presentation.helpers.Lastmin
import ru.vsibi.presentation.models.flight.FlightResult
import ru.vsibi.presentation.screens.tours.info.flights.main.FlightsMockFactory

class FlightsInfoChildFragment : BaseFragment<FragmentFligthsInfoChildBinding>(
    FragmentFligthsInfoChildBinding::inflate,
    R.layout.fragment_fligths_info_child
) {

    companion object {
        const val KEY_FLIGHTS = "key_flights"
    }

    private var flightResult: FlightResult? = null
    private var args: Bundle? = null
    private val adapter = FlightsInfoRecyclerViewAdapter()

    override fun onResume() {
        super.onResume()
        updateTitle()
    }
    override fun FragmentFligthsInfoChildBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        rvFlightsInfo.configure()
    }

    override fun initArguments() {
        args = arguments
    }

    override fun initData() {
        flightResult = args?.getParcelable(FlightsInfoFragment.KEY_FLIGHT_RESULT)
        val flights = FlightsMockFactory.createFlightsInfo(flightResult)
        adapter.setupAdapter(flights)
        flightResult?.date_from?.let {
            binding.tvDate.text = Lastmin.getStringDate(it * 1000)
        }
        updateTitle()
    }

    private fun RecyclerView.configure() {
        adapter = this@FlightsInfoChildFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun updateTitle(){
        (activity as AppCompatActivity).supportActionBar?.title = "${flightResult?.city_from} - ${flightResult?.city_to}"
    }
}

