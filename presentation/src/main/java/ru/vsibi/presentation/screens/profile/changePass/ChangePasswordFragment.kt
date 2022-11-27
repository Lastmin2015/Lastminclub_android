package ru.vsibi.presentation.screens.profile.changePass

import android.os.Build
import android.text.Editable
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.domain.network.post.PostChangePass
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentChangePasswordBinding
import ru.vsibi.presentation.databinding.FragmentCreatePasswordBinding
import ru.vsibi.presentation.helpers.Lastmin.containsLowerCase
import ru.vsibi.presentation.helpers.Lastmin.containsNumber
import ru.vsibi.presentation.helpers.Lastmin.containsUpperCase
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataViewState
import java.util.function.IntPredicate

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragment<FragmentChangePasswordBinding>(
    FragmentChangePasswordBinding::inflate,
    R.layout.fragment_change_password
) {

    private val viewModel: ChangePassViewModel by viewModels()
    override fun FragmentChangePasswordBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.change_pass)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun FragmentChangePasswordBinding.initListeners() {
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
        tietOldPass.doAfterTextChanged {
            if(tilOldPass.isErrorEnabled){
                tilOldPass.isErrorEnabled = false
            }
        }

        btnCreatePass.setOnClickListener {
            if(tietOldPass.text.isNullOrEmpty()){
                tilOldPass.error = getString(R.string.error_old_pass_empty)
                return@setOnClickListener
            }
            viewModel.obtainEvent(ChangePassEvent.ChangePassword(PostChangePass(tietPassSecond.text.toString().trim(), tietOldPass.text.toString().trim())))
        }
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: ChangePassViewState) {
        when (state) {
            is ChangePassViewState.Error -> {
                onError(state.error)
                if(state.error?.getErrorCode() == 403){
                    binding.tilOldPass.error = getString(R.string.error_pass_diff)
                }
                binding.progress.gone()
                binding.btnCreatePass.setText(R.string.change)
            }
            is ChangePassViewState.Loading -> {
                binding.progress.visible()
                binding.btnCreatePass.text = ""
            }
            is ChangePassViewState.PasswordChanged -> {
                snack("Password Was Changed")
                binding.progress.gone()
                binding.btnCreatePass.setText(R.string.change)

                popBack()
            }
        }
    }

    private fun bindViewActions(changePassAction: ChangePassAction) {

    }

    private fun FragmentChangePasswordBinding.validPassword(pass: Editable): Boolean {
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