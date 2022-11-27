package ru.vsibi.presentation.screens.tours.info.flights.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentFligthsInfoBinding
import ru.vsibi.presentation.screens.tours.info.flights.info.models.FlightsInfoModel

class FlightsInfoFragment :
    BaseFragment<FragmentFligthsInfoBinding>(FragmentFligthsInfoBinding::inflate, R.layout.fragment_fligths_info) {

    companion object {
        const val KEY_FLIGHT_RESULT = "key_flight_result"
    }
    private val args: FlightsInfoFragmentArgs by navArgs()

    override fun FragmentFligthsInfoBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.example_flight_name_common)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.viewpager.configure()
    }

    private fun ViewPager2.configure() {
        val fragments = mutableListOf<FlightsInfoModel>()
        args.flightInfo.result.forEach {
            val flightsInfoModel = FlightsInfoModel()
            flightsInfoModel.fragment.arguments = Bundle().apply {
                putParcelable(KEY_FLIGHT_RESULT, it)
            }
            fragments.add(flightsInfoModel)
        }
        adapter = FlightsInfoViewPagerAdapter(
            childFragmentManager, lifecycle, fragments
        )
        TabLayoutMediator(binding.tabDots, this) { tab, position ->
            tab.select()
        }.attach()
    }
}

