package ru.vsibi.presentation.screens.search.travallers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentTravellersBinding

class TravellersFragment :
    BaseFragment<FragmentTravellersBinding>(FragmentTravellersBinding::inflate, R.layout.fragment_travellers) {

    companion object {
        const val KEY_TRAVELLERS = "key_travellers"
    }

    private val viewModel: TravellersViewModel by viewModels()
    private val adapter = ChildsAdapter()

    override fun FragmentTravellersBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.travellers)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.apply {
            rvChilds.configure()
        }
    }

    override fun initArguments() {
        super.initArguments()
    }

    override fun initFragment() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.travellers)
    }

    override fun FragmentTravellersBinding.initListeners() {
        binding.apply {
            ibAdultMinus.setOnClickListener {
                viewModel.minusAdult()
            }
            ibAdultPlus.setOnClickListener {
                viewModel.plusAdult()
            }
            ibChildMinus.setOnClickListener {
                viewModel.minusChild()
                adapter.removeLast()
            }
            ibChildPlus.setOnClickListener {
                viewModel.plusChild()
                adapter.add(2)
            }
            btnOk.setOnClickListener {
                setFragmentResult(
                    KEY_TRAVELLERS,
                    Bundle().apply {
                        putParcelable(
                            KEY_TRAVELLERS,
                            TravellersModel(
                                binding.tvAdultCount.text.toString().toInt(),
                                binding.tvChildCount.text.toString().toInt()
                            )
                        )
                    })
                popBack()
            }
            btnCancel.setOnClickListener {
                popBack()
            }
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initObservers() {
        viewModel.adultsCountLiveData.observe(this) {
            binding.tvAdultCount.text = it.toString()
        }
        viewModel.childsCountLiveData.observe(this) {
            binding.tvChildCount.text = it.toString()
        }
    }

    private fun RecyclerView.configure() {
        adapter = this@TravellersFragment.adapter
        layoutManager = LinearLayoutManager(requireContext())
    }
}

