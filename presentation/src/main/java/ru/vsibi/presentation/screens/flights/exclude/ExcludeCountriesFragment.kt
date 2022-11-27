package ru.vsibi.presentation.screens.flights.exclude

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentExcludeCountriesBinding
import ru.vsibi.presentation.databinding.FragmentFligthsBinding

class ExcludeCountriesFragment : BaseFragment<FragmentExcludeCountriesBinding>(
    FragmentExcludeCountriesBinding::inflate,
    R.layout.fragment_exclude_countries
) {
    private val adapter = ExcludeCountriesAdapter()

    override fun FragmentExcludeCountriesBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.exclude_countries)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        rvCountries.configure()
//        relAddMore.setOnClickListener {
//            router.navigateToAddCountries()
//        }
    }

    override fun initData() {
        adapter.setupAdapter(CountriesFactory.getSelectedCountries())
    }

    private fun RecyclerView.configure() {
        adapter = this@ExcludeCountriesFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}
