package ru.vsibi.presentation.screens.flights.exclude.add

import androidx.appcompat.app.AppCompatActivity
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentAddCountriesBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible

class AddCountriesFragment : BaseFragment<FragmentAddCountriesBinding>(FragmentAddCountriesBinding::inflate, R.layout.fragment_add_countries) {

    override fun FragmentAddCountriesBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.exclude_countries)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

    }

    override fun FragmentAddCountriesBinding.initListeners() {
        search.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                ivMic.gone()
            }else{
                ivMic.visible()
            }
        }
    }

}