package ru.vsibi.presentation.screens.profile.orders.ticket

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.DialogTicketShareBinding
import ru.vsibi.presentation.databinding.FragmentTicketBinding

class TicketFragment : BaseFragment<FragmentTicketBinding>(FragmentTicketBinding::inflate, R.layout.fragment_ticket) {

    private var bottomSheetDialog: Dialog? = null

    override fun FragmentTicketBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.order_ticket_1)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        createBottomSheetDialog()
    }

    override fun initArguments() {
        super.initArguments()
    }

    override fun initFragment() {
        super.initFragment()
    }

    override fun FragmentTicketBinding.initListeners() {
        binding.apply {
            linShare.setOnClickListener {
                showShareDialog()
            }
        }
    }


    override fun initData() {
        super.initData()
    }

    override fun initObservers() {
        super.initObservers()
    }

    private fun createBottomSheetDialog() {
        bottomSheetDialog = BottomSheetDialog(requireContext())
        val bottomSheetBinding = DialogTicketShareBinding.inflate(layoutInflater, null, false)
        bottomSheetDialog?.setContentView(bottomSheetBinding.root)
    }

    private fun showShareDialog() {
        bottomSheetDialog?.show()
    }
}