package ru.vsibi.presentation.screens.profile.orders.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentOrdersBinding
import ru.vsibi.presentation.screens.tours.main.*

class OrdersFragment : BaseFragment<FragmentOrdersBinding>(FragmentOrdersBinding::inflate, R.layout.fragment_orders) {

    private val viewModel: OrdersViewModel by viewModels()
    private val itemsClickListener: (ResponseSearch.Result) -> Unit = { hotel ->
        router.navigateToOrdersDetail(hotel)
    }
    private val adapter = HotelsAdapter(itemsClickListener)

    override fun FragmentOrdersBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.my_orders)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.rvOrders.configure()
    }

    override fun initData() {
        viewModel.obtainEvent(OrdersEvent.Default)
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: OrdersViewState) {
        when (state) {
            is OrdersViewState.Loaded -> {
//                adapter.setupAdapter(state.data)
            }
        }
    }

    private fun bindViewActions(action: OrdersAction?) {

    }


    private fun RecyclerView.configure() {
        adapter = this@OrdersFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}