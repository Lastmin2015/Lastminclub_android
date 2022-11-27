package ru.vsibi.presentation.screens.tours.purchase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentPurchaseFormBinding
import ru.vsibi.presentation.helpers.Lastmin
import ru.vsibi.presentation.models.PersonalDataModel
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataFragment
import ru.vsibi.presentation.screens.tours.main.TourModel

class PurchaseFormFragment :
    BaseFragment<FragmentPurchaseFormBinding>(FragmentPurchaseFormBinding::inflate, R.layout.fragment_purchase_form) {

    private val viewModel: PurchaseFormViewModel by viewModels()
    private val args: PurchaseFormFragmentArgs by navArgs()

    override fun FragmentPurchaseFormBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.customer_purchase_form)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun initArguments() {
        viewModel.obtainEvent(PurchaseFormEvent.ConfigureArgs(args.tour))
    }

    override fun FragmentPurchaseFormBinding.initListeners() {
        setFragmentResultListener(PersonalDataFragment.KEY_PERSONAL_DATA) { requestKey, bundle ->
            val person = bundle.getParcelable(PersonalDataFragment.KEY_PERSONAL_DATA) as? PersonalDataModel
            viewModel.obtainEvent(PurchaseFormEvent.UpdatePerson(person))
        }
        tvAdult.setOnClickListener {
            router.navigateToPersonalDataFromPurchaseForm(viewModel.getAdult())
        }
        tvKid.setOnClickListener {
            router.navigateToPersonalDataFromPurchaseForm(viewModel.getChild())
        }
        btnAdd.setOnClickListener {
            router.navigateToAddCards()
        }
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: PurchaseFormState) {
        when (state) {
            is PurchaseFormState.Loaded -> updateViews(state.data)
        }
    }

    private fun bindViewActions(action: PurchaseFormAction?) {
        when(action){
            is PurchaseFormAction.AdultUpdated -> binding.updateAdult(action.person)
            is PurchaseFormAction.ChildUpdated -> binding.updateChild(action.person)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateViews(data: TourModel) {
        binding.apply {
            hotel.tvTitle.text = data.title
            hotel.tvDescription.text = data.location
            hotel.relCost.tvCost.text = "${data.currency} ${data.cost}"
            hotel.relCost.tvDate.text = "${data.dateStart} - ${data.dateEnd}"
            Glide.with(requireContext()).load(data.image).apply(Lastmin.listRequestOpts).into(hotel.image)
        }
    }

    private fun FragmentPurchaseFormBinding.updateAdult(person : PersonalDataModel){
        tvAdult.setText(person.name)
    }
    private fun FragmentPurchaseFormBinding.updateChild(person : PersonalDataModel){
        tvKid.setText(person.name)
    }
}