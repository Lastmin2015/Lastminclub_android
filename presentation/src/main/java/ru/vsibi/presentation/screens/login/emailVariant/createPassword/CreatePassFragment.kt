package ru.vsibi.presentation.screens.login.emailVariant.createPassword

import android.os.Build
import android.text.Editable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentCreatePasswordBinding
import ru.vsibi.presentation.helpers.Lastmin.containsLowerCase
import ru.vsibi.presentation.helpers.Lastmin.containsNumber
import ru.vsibi.presentation.helpers.Lastmin.containsUpperCase
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import java.util.function.IntPredicate

@AndroidEntryPoint
class CreatePassFragment : BaseFragment<FragmentCreatePasswordBinding>(
    FragmentCreatePasswordBinding::inflate,
    R.layout.fragment_create_password
) {

    private val viewModel: CreatePassViewModel by viewModels()
    private val args: CreatePassFragmentArgs by navArgs()

    override fun FragmentCreatePasswordBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun initArguments() {
        viewModel.obtainEvent(CreatePassEvent.ConfigureArgs(args.email))
    }

    override fun FragmentCreatePasswordBinding.initListeners() {
        tietPassFirst.doAfterTextChanged { pass ->
            if (pass == null) return@doAfterTextChanged
            if (pass.isEmpty()) {
                iv10Charachters.setImageResource(R.drawable.ic_pass_have_no)
                ivOneNumber.setImageResource(R.drawable.ic_pass_have_no)
                ivOneUppercase.setImageResource(R.drawable.ic_pass_have_no)
                ivOneLowercase.setImageResource(R.drawable.ic_pass_have_no)
                return@doAfterTextChanged
            }
            val isValidPass = binding.validPassword(pass)
            tietPassSecond.isEnabled = isValidPass
        }
        tietPassSecond.doAfterTextChanged {
            val isValidPass = tietPassFirst.text.toString() == tietPassSecond.text.toString()
            btnCreatePass.isEnabled = isValidPass
        }
        btnCreatePass.setOnClickListener {
            viewModel.obtainEvent(CreatePassEvent.SignIn(tietPassFirst.text.toString()))
        }
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }


    private fun bindViewState(state: CreatePassViewState) {
        when (state) {
            is CreatePassViewState.Default -> {
                binding.progress.gone()
            }
            is CreatePassViewState.LoggedIn -> {
                binding.progress.gone()
                router.navigateToMainFromCreatePass()
            }
            is CreatePassViewState.Error -> {
                binding.progress.gone()
                onError(state.error)
            }
            is CreatePassViewState.Loaded -> {
            }
            is CreatePassViewState.Loading -> {
                binding.progress.visible()
            }
        }
    }

    private fun bindViewActions(action: CreatePassAction?) {

    }


    private fun FragmentCreatePasswordBinding.validPassword(pass: Editable): Boolean {
        var isValid = true
        if (pass.length <= 10) {
            isValid = false
            iv10Charachters.setImageResource(R.drawable.ic_pass_error)
        } else {
            iv10Charachters.setImageResource(R.drawable.ic_pass_check)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (containsNumber(pass.toString())) {
                ivOneNumber.setImageResource(R.drawable.ic_pass_check)
            } else {
                isValid = false
                ivOneNumber.setImageResource(R.drawable.ic_pass_error)
            }
            if (containsUpperCase(pass.toString())) {
                ivOneUppercase.setImageResource(R.drawable.ic_pass_check)
            } else {
                isValid = false
                ivOneUppercase.setImageResource(R.drawable.ic_pass_error)
            }
            if (containsLowerCase(pass.toString())) {
                ivOneLowercase.setImageResource(R.drawable.ic_pass_check)
            } else {
                isValid = false
                ivOneLowercase.setImageResource(R.drawable.ic_pass_error)
            }
        }
        return isValid
    }

}