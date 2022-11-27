package ru.vsibi.presentation.screens.login.emailVariant.password

import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.helper.IError
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentLoginPasswordBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible

@AndroidEntryPoint
class LoginPasswordFragment : BaseFragment<FragmentLoginPasswordBinding>(
    FragmentLoginPasswordBinding::inflate,
    R.layout.fragment_login_password
) {

    private val args: LoginPasswordFragmentArgs by navArgs()
    private val viewModel: LoginPasswordViewModel by viewModels()

    override fun FragmentLoginPasswordBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.enter_password)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun initArguments() {
        viewModel.obtainEvent(LoginPasswordEvent.ConfigureArgs(args.email))
    }

    override fun initFragment() {
        viewModel.obtainEvent(LoginPasswordEvent.Default())
    }

    override fun FragmentLoginPasswordBinding.initListeners() {
        btnSignIn.setOnClickListener {
            viewModel.obtainEvent(LoginPasswordEvent.SignIn(tietPassword.text.toString().trim()))
        }
        btnForgotPass.setOnClickListener {
            router.navigateForgotPass(args.email)
        }
        tietPassword.doAfterTextChanged {
            if (tilPassword.isErrorEnabled) {
                tilPassword.isErrorEnabled = false
            }
        }
    }

    override fun initData() {
        super.initData()
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: LoginPasswordViewState) {
        when (state) {
            is LoginPasswordViewState.Loaded -> updateViews(state.data)
            is LoginPasswordViewState.Default -> {
                binding.progress.gone()
            }
            is LoginPasswordViewState.LoggedIn -> {
                binding.progress.gone()
                router.navigateToMainFromEmailLogin()
            }
            is LoginPasswordViewState.Error -> {
                binding.progress.gone()
                onError(state.error)
            }
            is LoginPasswordViewState.Loading -> binding.progress.visible()
        }
    }

    private fun bindViewActions(action: LoginPasswordAction?) {
        when (action) {
            is LoginPasswordAction.PasswordEmpty -> passError(getString(R.string.empty_password))
        }
    }

    private fun passError(errorMsg: String) {
        binding.tilPassword.error = errorMsg
    }

    private fun updateViews(data: String) {
        binding.tvPassDesc.text = "${getString(R.string.enter_password_desc)} $data"
    }

    override fun onError(error: IError?) {
        if (error?.getErrorCode() == 403) {
            passError(getString(R.string.incorrect_password))
        } else {
            super.onError(error)
        }
    }

}