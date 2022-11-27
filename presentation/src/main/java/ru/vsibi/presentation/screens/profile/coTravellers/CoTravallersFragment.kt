package ru.vsibi.presentation.screens.profile.coTravellers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentSoTravellersBinding
import ru.vsibi.presentation.helpers.SwipeToDeleteCallback
import ru.vsibi.presentation.models.PersonalDataModel
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataFragment.Companion.KEY_PERSONAL_DATA


class CoTravallersFragment :
    BaseFragment<FragmentSoTravellersBinding>(FragmentSoTravellersBinding::inflate, R.layout.fragment_so_travellers) {

    private val itemsClickListener: (PersonalDataModel) -> Unit = { person ->
        router.navigateToPersonalDataFromCoTravellers(person)
    }
    private val adapter = CoTravellersAdapter(itemsClickListener)

    override fun FragmentSoTravellersBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.co_travellers)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.apply {
            linAddNew.setOnClickListener {
                router.navigateToPersonalDataFromCoTravellers(null)
            }
            rvCoTravellers.configure()
        }
    }

    override fun initData() {

    }

    override fun FragmentSoTravellersBinding.initListeners() {
        setFragmentResultListener(KEY_PERSONAL_DATA) { requestKey, bundle ->
            val person = bundle.getParcelable(KEY_PERSONAL_DATA) as? PersonalDataModel
            adapter.add(person)
        }
    }

    private fun RecyclerView.configure() {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this@CoTravallersFragment.adapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                this@CoTravallersFragment.adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(this)
    }
}
