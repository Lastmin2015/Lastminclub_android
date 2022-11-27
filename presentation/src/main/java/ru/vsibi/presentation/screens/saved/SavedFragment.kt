package ru.vsibi.presentation.screens.saved

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentSavedBinding
import ru.vsibi.presentation.screens.tours.main.HotelsAdapter
import ru.vsibi.presentation.screens.tours.main.TourModel

class SavedFragment : BaseFragment<FragmentSavedBinding>(FragmentSavedBinding::inflate, R.layout.fragment_saved) {

    private val viewModel: SavedViewModel by viewModels()
    private val itemsClickListener: (ResponseSearch.Result) -> Unit = { hotel ->
        router.navigateToOrdersDetailFromSaved(hotel)
    }
    private val adapter = HotelsAdapter(itemsClickListener)

    override fun FragmentSavedBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.saved)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        binding.rvSaved.configure()
    }

    override fun initData() {
        viewModel.obtainEvent(SavedEvent.Default)
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: SavedViewState) {
        when (state) {
            is SavedViewState.Loaded -> {
//                adapter.setupAdapter(state.data)
            }
        }
    }

    private fun bindViewActions(action: SavedAction?) {}

    private fun RecyclerView.configure() {
        adapter = this@SavedFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }

}