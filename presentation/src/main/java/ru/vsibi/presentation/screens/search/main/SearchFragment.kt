package ru.vsibi.presentation.screens.search.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Pair
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.domain.network.query.QuerySearchModel
import ru.vsibi.presentation.R
//import ru.vsibi.
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentSearchBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.screens.search.travallers.TravellersFragment
import ru.vsibi.presentation.screens.search.travallers.TravellersModel
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate, R.layout.fragment_search) {

    private var picker: MaterialDatePicker<Pair<Long, Long>>? = null
    private val viewModel: SearchViewModel by viewModels()
    private var isPickerOpened = false

    private var travellersModel: TravellersModel? = null
    private var dateStartEnd: Pair<Long, Long>? = null

    override fun onResume() {
        super.onResume()
        isPickerOpened = false
    }

    override fun FragmentSearchBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.search)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        initRangeDatePicker()
    }


    override fun initArguments() {

    }

    override fun initFragment() {

    }

    override fun FragmentSearchBinding.initListeners() {
        setFragmentResultListener(TravellersFragment.KEY_TRAVELLERS) { requestKey, bundle ->
            travellersModel =
                bundle.getParcelable(TravellersFragment.KEY_TRAVELLERS) as? TravellersModel
            binding.tvTravellers.setText("" + travellersModel?.adultsCount + " Adults, " + travellersModel?.childsCount + " Kids")
            viewModel.obtainEvent(SearchEvent.UpdatePersonsDesc("" + travellersModel?.adultsCount + " + " + travellersModel?.childsCount))
        }
        tvTravellers.setOnClickListener {
            router.navigateToTravellers()
        }
        btnSearch.setOnClickListener {
            viewModel.obtainEvent(
                SearchEvent.UpdateCountry(
                    tvDestionation.text.toString().trim()
                )
            )
            viewModel.obtainEvent(
                SearchEvent.StartSearch(
                    QuerySearchModel(
                        origin = tvOrigin.text.toString().trim(),
                        destination = tvDestionation.text.toString().trim(),
                        startDate = dateStartEnd?.first ?: System.currentTimeMillis(),
                        endDate = dateStartEnd?.second ?: System.currentTimeMillis(),
                        adults = travellersModel?.adultsCount ?: 0,
                        children = travellersModel?.childsCount ?: 0,
                        withData = true
                    )
                )
            )
        }
        includeApiError.btnTryAgain.setOnClickListener {
            viewModel.obtainEvent(SearchEvent.FetchCountries())
        }
        tvOrigin.doAfterTextChanged {
            if(tilOrigin.isErrorEnabled){
                tilOrigin.isErrorEnabled = false
            }
        }
        tvDestionation.doAfterTextChanged {
            if(tilDestination.isErrorEnabled){
                tilDestination.isErrorEnabled = false
            }
        }
    }

    override fun initData() {
        binding.apply {
            tilDate.visible()
            tilDestination.visible()
            tilOrigin.visible()
            tilTravellers.visible()
            btnSearch.visible()
            ivLoaderLogo.gone()
            includeApiError.root.gone()
        }
        initOriginSpinner(emptyList())
        initDestinationSpinner(emptyList())
        router.mainFragmentInstance?.showNavigation()
        viewModel.obtainEvent(SearchEvent.FetchCountries())
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: SearchViewState) {
        when (state) {
            is SearchViewState.SearchLoading -> {
                binding.progress.visibility = View.VISIBLE
                binding.btnSearch.alpha = 0.5f
            }
            is SearchViewState.SearchFinished -> {
                binding.progress.visibility = View.GONE
                binding.btnSearch.alpha = 1f
                viewModel.getSearchModel()?.let {
                    router.navigateHotels(it)
                }
                viewModel.obtainEvent(SearchEvent.Default())
            }
            is SearchViewState.Default -> {
            }
            is SearchViewState.Error -> {
                binding.progress.visibility = View.GONE
                binding.btnSearch.alpha = 1f
                onError(state.error)
            }
            is SearchViewState.LoadedData -> {
                binding.apply {
                    tilDate.visible()
                    tilDestination.visible()
                    tilOrigin.visible()
                    tilTravellers.visible()
                    btnSearch.visible()
                    ivLoaderLogo.gone()
                    includeApiError.root.gone()
                }
                initOriginSpinner(state.list)
                initDestinationSpinner(state.list)
                router.mainFragmentInstance?.showNavigation()
            }
            is SearchViewState.ErrorData -> {
                binding.apply {
                    tilDate.gone()
                    tilDestination.gone()
                    tilOrigin.gone()
                    tilTravellers.gone()
                    btnSearch.gone()
                    ivLoaderLogo.gone()
                    includeApiError.root.visible()
                    state.error?.getErrorResource()?.let { includeApiError.tvError.setText(it) }
                }

            }
            is SearchViewState.LoadingData -> {
                binding.apply {
                    tilDate.gone()
                    tilDestination.gone()
                    tilOrigin.gone()
                    tilTravellers.gone()
                    btnSearch.gone()
                    ivLoaderLogo.visible()
                    includeApiError.root.gone()
                }
                router.mainFragmentInstance?.hideNavigation()
            }
            is SearchViewState.SearchInvalidFields -> {
                if(state.isInvalidOrigin){
                    binding.tilOrigin.error = getString(R.string.required)
                }
                if(state.isInvalidDestination){
                    binding.tilDestination.error = getString(R.string.required)
                }
            }
        }
    }

    private fun bindViewActions(action: SearchAction?) {}


    private fun initOriginSpinner(list: List<String>) {
        val editTextFilledExposedDropdown = binding.tvOrigin
        val clickListener = View.OnClickListener {
            val popupMenu = PopupMenu(
                requireContext(),
                editTextFilledExposedDropdown,
                Gravity.END,
                0,
                R.style.PopupMenuMoreCentralized
            )
            for (i in list.indices) {
                popupMenu.menu.add(i, Menu.FIRST, i, list[i])
            }
            popupMenu.setOnMenuItemClickListener { menuItem ->
                editTextFilledExposedDropdown.setText(menuItem.title)
                false
            }
            popupMenu.show()
        }
        binding.tilOrigin.setEndIconOnClickListener(clickListener)
        editTextFilledExposedDropdown.setOnClickListener(clickListener)
    }

    private fun initDestinationSpinner(list: List<String>) {
        val editTextFilledExposedDropdown = binding.tvDestionation
        val clickListener = View.OnClickListener {
            val popupMenu = PopupMenu(
                requireContext(),
                editTextFilledExposedDropdown,
                Gravity.END,
                0,
                R.style.PopupMenuMoreCentralized
            )
            for (i in list.indices) {
                popupMenu.menu.add(i, Menu.FIRST, i, list[i])
            }
            popupMenu.setOnMenuItemClickListener { menuItem ->
                editTextFilledExposedDropdown.setText(menuItem.title)
                false
            }
            popupMenu.show()
        }
        binding.tilDestination.setEndIconOnClickListener(clickListener)
        editTextFilledExposedDropdown.setOnClickListener(clickListener)
    }

    @SuppressLint("RestrictedApi")
    private fun initRangeDatePicker() {
        val selector = CustomRangeSelector()
        val builder = MaterialDatePicker.Builder.customDatePicker(selector)
        picker = builder.build()
        binding.tvDate.setOnClickListener {
            if (isPickerOpened) return@setOnClickListener
            isPickerOpened = true

//            router.openDateRangeDialog()
            picker?.setRadioListener {
                selector.switchRadioMode(it)
                picker?.notifyAdapter()
            }
            selector.setupNotifyListener { day ->
                picker?.notifySelect(day)
            }
            picker?.show(childFragmentManager, picker.toString())
            picker?.addOnPositiveButtonClickListener {
                dateStartEnd = it
                val formatter = SimpleDateFormat("dd.MM");
                val dateString = formatter.format(Date(it.first));
                binding.tvDate.setText(picker?.headerText)
                viewModel.obtainEvent(SearchEvent.UpdateDate(dateString))
                Log.d(
                    "DatePicker Activity",
                    "Date String = ${picker?.headerText}::  Date epoch values::${it.first}:: to :: ${it.second}"
                )
            }
            picker?.addOnCancelListener {
                isPickerOpened = false
            }
            picker?.addOnDismissListener {
                isPickerOpened = false
            }
        }
    }

}

