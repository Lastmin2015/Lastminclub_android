package ru.vsibi.presentation.screens.tours.info.more

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentTourMoreBinding
import ru.vsibi.presentation.screens.tours.info.ToursInfoFragmentArgs

class TourMoreFragment :
    BaseFragment<FragmentTourMoreBinding>(
        FragmentTourMoreBinding::inflate,
        R.layout.fragment_tour_more
    ) {

    private val args: TourMoreFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun FragmentTourMoreBinding.initViews() {
        toolbar.setNavigationOnClickListener {
            popBack()
        }
        if (args.tour.tour.hotel.hotel_descriptions.isNotEmpty()) {
            tvMainInfo.text =args.tour.tour.hotel.hotel_descriptions[0].description
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }
}