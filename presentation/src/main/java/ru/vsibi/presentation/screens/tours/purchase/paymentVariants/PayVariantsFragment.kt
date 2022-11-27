package ru.vsibi.presentation.screens.tours.purchase.paymentVariants

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentPayVariantsBinding
import ru.vsibi.presentation.databinding.FragmentPurchaseFormBinding
import ru.vsibi.presentation.models.CardModel
import ru.vsibi.presentation.screens.tours.main.TourModel

class PayVariantsFragment :
    BaseFragment<FragmentPayVariantsBinding>(FragmentPayVariantsBinding::inflate, R.layout.fragment_pay_variants) {

    private val itemsClickListener: (CardModel, Boolean, Int) -> Unit = { card, isChecked, position ->
        adapter.select(isChecked, position)
    }
    private val adapter = PayVariantsAdapter(itemsClickListener)

    override fun FragmentPayVariantsBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.pay_with)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        rvCards.configure()
        tvAddNew.setOnClickListener {
            router.navigateToAddCard()
        }
    }

    override fun initData() {
        adapter.setupAdapter(
            mutableListOf(
                CardModel("Visa", "•••• 1242", R.drawable.ic_visa_card),
                CardModel("Visa", "•••• 1242", R.drawable.ic_visa_card),
                CardModel("MasterCard", "•••• 1242", R.drawable.ic_mastercard),
                CardModel("MasterCard", "•••• 1242", R.drawable.ic_mastercard)
            )
        )
    }

    private fun RecyclerView.configure() {
        adapter = this@PayVariantsFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}
