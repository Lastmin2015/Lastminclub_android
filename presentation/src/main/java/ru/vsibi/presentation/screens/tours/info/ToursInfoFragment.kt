package ru.vsibi.presentation.screens.tours.info

import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.getDateDayMonth
import ru.vsibi.helper.getPlaceText
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentHotelsInfoBinding
import ru.vsibi.presentation.helpers.Lastmin

@AndroidEntryPoint
class ToursInfoFragment :
    BaseFragment<FragmentHotelsInfoBinding>(
        FragmentHotelsInfoBinding::inflate,
        R.layout.fragment_hotels_info
    ) {

    private val args: ToursInfoFragmentArgs by navArgs()
    private val viewModel: ToursInfoViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
//        initBoardingSpinner()
//        initRoomsSpinner()
//        initTransferSpinner()
    }

    override fun FragmentHotelsInfoBinding.initViews() {

    }

    override fun initArguments() {
        viewModel.obtainEvent(ToursInfoEvent.ConfigureArgs(args.tour))
    }

    override fun initFragment() {
        super.initFragment()
    }

    override fun FragmentHotelsInfoBinding.initListeners() {
        binding.apply {
            fabBack.setOnClickListener {
                popBack()
            }
            btnBook.setOnClickListener {
//                viewModel.tour?.let { it1 -> router.navigatePurchaseForm(it1) }
            }
            tvFlightDetails.setOnClickListener {
//                router.navigateToFlightDetails()
            }
        }
    }

    override fun initData() {
//        viewModel.obtainEvent(ToursInfoEvent.FetchHotel("615084401abe2b00089b29c3"))
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: ToursInfoState) {
        when (state) {
            is ToursInfoState.Loaded -> updateViews(state.data)
        }
    }

    private fun bindViewActions(action: ToursInfoAction?) {

    }

    private fun updateViews(data: ResponseSearch.Result) {
        binding.apply {
            /**
             * for arguments data
             */
            with(data){
                tvDates.text =
                    getDateDayMonth(this.tour.outbound_flight.date_from) + " - " + getDateDayMonth(
                        this.tour.return_flight.date_to
                    )
                tvPrice.text = "€" + this.price
                with(this.tour.hotel) {
                    tvTitle.text = this.name
                    val regionName = this.place.resort.region.region_name?.name ?: "Resort"
                    val countryName = this.place.resort.region.country.country_name?.name
                    val resortName = this.place.resort.resort_name.name
                    tvLocation.text = getPlaceText(regionName, countryName, resortName)
                    if (this.hotel_descriptions.isNotEmpty()) {
                        tvDescription.text = this.hotel_descriptions[0].description
                    }
                    if (this.hotel_images.isNotEmpty()) {
                        Glide.with(root.context)
                            .load(Lastmin.getImageUrl(this.hotel_images[0].file_name)).into(image)
                    }
                    tvDistanceAirport.text = this.distance_to_slope.toString() + getString(R.string.km)
                    tvDistanceBeach.text = this.distance_to_beach.toString() + getString(R.string.km)
                    tvDistanceCity.text = this.distance_to_city.toString() + getString(R.string.km)
                    with(this.hotel_images.size) {
                        if (this != 0) {
                            tvImageSelector.text = "1/${this}"
                        }
                    }

                    tvMore.setOnClickListener {
                        router.navigateTourMore(data)
                    }
                    image.setOnClickListener {
                        val photoList = this.hotel_images.map { hotelImage ->
                            hotelImage.file_name
                        }
                        photoList.forEach {
                            log("photo " + it)
                        }
                        router.navigatePhotoViewerFromTourInfo(photoList)
                    }
                }
            }

//            with(data) {
//                tvTitle.text = this.name
//                val regionName = this.place.resort.region.region_name?.name ?: "Resort"
//                val countryName = this.place.resort.region.country.country_name?.name
//                tvLocation.text = getPlaceText(regionName, countryName)
//                if (data.hotel_descriptions.isNotEmpty()) {
//                    tvDescription.text = data.hotel_descriptions[0].description
//                }
//                if (this.hotel_images.isNotEmpty()) {
//                    Glide.with(root.context)
//                        .load(Lastmin.getImageUrl(this.hotel_images[0].file_name)).into(image)
//                }
//                tvDistanceAirport.text = this.distance_to_slope.toString() + getString(R.string.km)
//                tvDistanceBeach.text = this.distance_to_beach.toString() + getString(R.string.km)
//                tvDistanceCity.text = this.distance_to_city.toString() + getString(R.string.km)
//                with(this.hotel_images.size) {
//                    if (this != 0) {
//                        tvImageSelector.text = "1/${this}"
//                    }
//                }
//                tvDates.text =
//                    getDateDayMonth(args.tour.tour.outbound_flight.date_from) + " - " + getDateDayMonth(
//                        args.tour.tour.return_flight.date_to
//                    )
//                tvPrice.text = "€" + args.tour.price
//
//                tvMore.setOnClickListener {
//                    router.navigateTourMore(data)
//                }
//                image.setOnClickListener {
//                    val photoList = this.hotel_images.map { hotelImage ->
//                        hotelImage.file_name
//                    }
//                    photoList.forEach {
//                        log("photo " + it)
//                    }
//                    router.navigatePhotoViewerFromTourInfo(photoList)
//                }
//            }
        }
    }

    private fun initBoardingSpinner() {
        val adapter = TourMenuAdapter(
            requireContext(),
            R.layout.cell_spinner_tour, TourInfoFactory.getBoardingList()
        )
        val editTextFilledExposedDropdown = binding.tvBoarding
        editTextFilledExposedDropdown.setAdapter(adapter)
        editTextFilledExposedDropdown.setOnClickListener {
            it as AutoCompleteTextView
            if (!it.isShown) {
                it.showDropDown()
            }
        }
        editTextFilledExposedDropdown.setOnItemClickListener { parent, view, position, id ->
            binding.tvBoarding.setText(
                (binding.tvBoarding.adapter.getItem(position) as TourCellModel).title,
                false
            );
        }
    }

    private fun initRoomsSpinner() {
        val adapter = TourMenuAdapter(
            requireContext(),
            R.layout.cell_spinner_tour, TourInfoFactory.getRoomTypesList()
        )
        val editTextFilledExposedDropdown = binding.tvRoomType
        editTextFilledExposedDropdown.setAdapter(adapter)
        editTextFilledExposedDropdown.setOnClickListener {
            it as AutoCompleteTextView
            if (!it.isShown) {
                it.showDropDown()
            }
        }
        editTextFilledExposedDropdown.setOnItemClickListener { parent, view, position, id ->
            binding.tvRoomType.setText(
                (binding.tvRoomType.adapter.getItem(position) as TourCellModel).title,
                false
            );
        }
    }

    private fun initTransferSpinner() {
        val adapter = TourMenuAdapter(
            requireContext(),
            R.layout.cell_spinner_tour, TourInfoFactory.getTransferList()
        )
        val editTextFilledExposedDropdown = binding.tvTransfer
        editTextFilledExposedDropdown.setAdapter(adapter)
        editTextFilledExposedDropdown.setOnClickListener {
            it as AutoCompleteTextView
            if (!it.isShown) {
                it.showDropDown()
            }
        }
        editTextFilledExposedDropdown.setOnItemClickListener { parent, view, position, id ->
            binding.tvTransfer.setText(
                (binding.tvTransfer.adapter.getItem(position) as TourCellModel).title,
                false
            );
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

}