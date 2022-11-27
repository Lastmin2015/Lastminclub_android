package ru.vsibi.presentation.screens.tours.purchase.paymentVariants.add

import androidx.appcompat.app.AppCompatActivity
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentAddCardBinding
import ru.vsibi.presentation.helpers.FourDigitCardFormatWatcher

class AddCardFragment : BaseFragment<FragmentAddCardBinding>(FragmentAddCardBinding::inflate, R.layout.fragment_add_card) {

    override fun FragmentAddCardBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.add_new_card)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun FragmentAddCardBinding.initListeners() {
        tietCard.addTextChangedListener(FourDigitCardFormatWatcher())
        btnSave.setOnClickListener {
            popBack()
        }
    }
}