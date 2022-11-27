package ru.vsibi.presentation.screens.profile.orders.info

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentOrdersDetailBinding
import ru.vsibi.presentation.screens.tours.main.TourModel

class OrdersDetailFragment : BaseFragment<FragmentOrdersDetailBinding>(FragmentOrdersDetailBinding::inflate, R.layout.fragment_orders_detail) {

//    private val args : OrdersDetailFragmentArgs by navArgs()

    override fun FragmentOrdersDetailBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.my_orders)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun initArguments() {
//        updateViews(args.tour)
    }

    override fun initFragment() {
        super.initFragment()
    }

    override fun FragmentOrdersDetailBinding.initListeners() {
        binding.relTicket1.setOnClickListener {
            router.navigateToTicket()
        }
        binding.relTicket2.setOnClickListener {
            router.navigateToTicket()
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initObservers() {
        super.initObservers()
    }

    private fun updateViews(hotel: TourModel) {
        binding.apply {
            tvTitle.text = hotel.title
            tvLocation.text = hotel.location
        }
    }
}