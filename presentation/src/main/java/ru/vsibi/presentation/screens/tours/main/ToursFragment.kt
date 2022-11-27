package ru.vsibi.presentation.screens.tours.main

import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentToursBinding
import ru.vsibi.presentation.screens.tours.main.settings.ToursSettingsFragment


class ToursFragment : BaseFragment<FragmentToursBinding>(FragmentToursBinding::inflate, R.layout.fragment_tours) {

    private val viewModel: HotelsViewModel by viewModels()
    private val args: ToursFragmentArgs by navArgs()
    private var isSmall = true
    private val itemsClickListener: (ResponseSearch.Result) -> Unit = { hotel ->
        router.navigateToHotelsInfo(hotel)
    }
    private val adapter = HotelsAdapter(itemsClickListener)

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
        checkItemSize()
    }

    override fun FragmentToursBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "${getArgsTitle()}"

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHasOptionsMenu(true)
        }
        binding.rvHotels.configure()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tours_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        router.navigateToursSettings()
        return true
    }

    override fun FragmentToursBinding.initListeners() {
        setFragmentResultListener(ToursSettingsFragment.TOURS_SETTINGS_KEY) { requestKey, bundle ->
            isSmall = bundle.getBoolean(ToursSettingsFragment.IS_SMALL)
            checkItemSize()
        }
    }

    override fun initData() {
        viewModel.obtainEvent(HotelsEvent.Default)
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: HotelsViewState) {
        when (state) {
            is HotelsViewState.Loaded -> {
                adapter.setupAdapter(args.search.result)
            }
        }
    }

    private fun bindViewActions(action: HotelsAction?) {}

    private fun RecyclerView.configure() {
        adapter = this@ToursFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getArgsTitle(): CharSequence? {
//        if (args.search.date == null && args.search.personsDesc == null) {
            return getString(R.string.tours)
//        } else {
//            return if (args.search.date == null) {
//                if (args.search.personsDesc == null) {
//                    getString(R.string.tours)
//                } else {
//                    args.search.personsDesc
//                }
//            } else {
//                if (args.search.personsDesc == null) {
//                    args.search.date
//                } else {
//                    args.search.date + ", " + args.search.personsDesc
//                }
//            }
//        }
    }

    private fun checkItemSize() {
        if (isSmall) {
            displaySmallSize()
        } else {
            displayBigSize()
        }
    }

    private fun displayBigSize() {
        adapter.displayBigSize()
        binding.rvHotels.setPadding(0, 0, 0, getDp(64f).toInt())
    }

    private fun displaySmallSize() {
        adapter.displaySmallSize()
        binding.rvHotels.setPadding(0, getDp(20f).toInt(), 0, getDp(64f).toInt())
    }

    private fun getDp(float: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, float,
            requireContext().resources.displayMetrics
        )
    }
}

