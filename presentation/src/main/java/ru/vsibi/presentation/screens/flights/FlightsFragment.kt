package ru.vsibi.presentation.screens.flights

import androidx.appcompat.app.AppCompatActivity
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentFligthsBinding

class FlightsFragment : BaseFragment<FragmentFligthsBinding>(FragmentFligthsBinding::inflate, R.layout.fragment_fligths) {

    override fun FragmentFligthsBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.flights_search_opts)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        tvExcludeCountries.setOnClickListener {
            router.navigateToExcludeCountries()
        }
    }
}