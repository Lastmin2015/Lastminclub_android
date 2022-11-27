package ru.vsibi.presentation.screens.login

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentLoginBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.screens.login.emailVariant.createPassword.CreatePassAction
import ru.vsibi.presentation.screens.login.emailVariant.createPassword.CreatePassViewState

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate, R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var callbackManager: CallbackManager

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        handleSignInResultGoogle(task)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun FragmentLoginBinding.initViews() {
    }

    override fun initArguments() {
        super.initArguments()
    }

    override fun initFragment() {
        initFacebook()
        initGoogle()
    }

    override fun FragmentLoginBinding.initListeners() {
        btnEmail.setOnClickListener {
            router.navigateToLoginWithEmail(isLogin = true)
        }
        btnCreate.setOnClickListener {
            router.navigateToLoginWithEmail(isLogin = false)
        }
        btnFacebook.setOnClickListener {
            signInFacebook()
        }
        btnGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun initGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.server_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }


    private fun initFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    if (Profile.getCurrentProfile() == null) {
                        object : ProfileTracker() {
                            override fun onCurrentProfileChanged(
                                oldProfile: Profile?,
                                currentProfile: Profile
                            ) {
                                handleSignInResultFacebook(currentProfile)
                                this.stopTracking()
                            }
                        }
                    } else {
                        val profile = Profile.getCurrentProfile()
                        handleSignInResultFacebook(profile)
                    }

                }

                override fun onCancel() {

                }

                override fun onError(error: FacebookException?) {
                    log(error?.localizedMessage)
                }
            })
    }

    private fun signInGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun signInFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile"));
    }

    private fun handleSignInResultFacebook(profile: Profile) {
        Log.d(TAG, "id " + profile.id)
        Log.d(TAG, "firstName " + profile.firstName)
        Log.d(TAG, "lastName " + profile.lastName)
        Log.d(TAG, "name " + profile.name)
        val accessToken = AccessToken.getCurrentAccessToken()
        viewModel.obtainEvent(LoginEvent.LoginWithFacebook(accessToken.token))
    }

    private fun handleSignInResultGoogle(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.idToken?.let {
                LoginEvent.LoginWithGoogle(it)
            }?.let {
                viewModel.obtainEvent(it)
            }
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode())
        }
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }


    private fun bindViewState(state: LoginViewState) {
        when (state) {
            is LoginViewState.Error -> {
                onError(state.error)
            }
            is LoginViewState.Loading -> {}
            is LoginViewState.LoggedIn -> {
                router.navigateToMainFromLogin()
            }
            is LoginViewState.SignCanceled -> { }
            is LoginViewState.SignError -> {
                onError(state.message)
            }
        }
    }

    private fun bindViewActions(action: LoginAction?) {

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)
    }
}