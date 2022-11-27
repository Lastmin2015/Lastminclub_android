package ru.vsibi.presentation.screens.tours.info.flights.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentFligthsListBinding
import ru.vsibi.presentation.models.flight.FlightResult

class FlightsListFragment :
    BaseFragment<FragmentFligthsListBinding>(FragmentFligthsListBinding::inflate, R.layout.fragment_fligths_list) {

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
    private val viewModel : FlightsListViewModel by viewModels()
    private val itemsClickListener: (FlightResult) -> Unit = { flight ->
        router.navigateToFlightInfo(viewModel.getFligths())
    }
    private val adapter = FlightsAdapter(itemsClickListener)

    override fun FragmentFligthsListBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.flights_details)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.rvFlights.configure()
    }

    override fun initData() {
        viewModel.obtainEvent(FlightsListEvent.FetchData())
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: FlightsListState) {
        when (state) {
            is FlightsListState.Loaded -> {
                adapter.setupAdapter(state.data.result)
            }
        }
    }

    private fun bindViewActions(action: FlightsListAction?) {}


    private fun RecyclerView.configure() {
        adapter = this@FlightsListFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}

