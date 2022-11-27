package ru.vsibi.presentation.screens.profile.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.domain.network.response.ResponseProfile
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentProfileBinding
import ru.vsibi.presentation.helpers.Lastmin.circleOprions
import ru.vsibi.presentation.helpers.Lastmin.getImageUrl
import ru.vsibi.presentation.models.PersonalDataModel
import ru.vsibi.presentation.screens.profile.main.ProfilePhotoActionDialog.Companion.REQUEST_ADD_PHOTO
import ru.vsibi.presentation.screens.profile.main.ProfilePhotoActionDialog.Companion.REQUEST_OPEN_PHOTO
import ru.vsibi.presentation.screens.profile.personalData.PersonalDataFragment


@AndroidEntryPoint
class ProfileFragment :
    BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate, R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    private val imageLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        val uri = it.data?.data
        log("data " + uri)
        uri?.let { letUri ->
            viewModel.obtainEvent(ProfileEvent.UploadPhoto(requireContext().contentResolver, letUri.toString()))
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            addPhoto()
        }

    override fun FragmentProfileBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.profile)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
        Glide.with(requireContext()).load(R.drawable.profile)
            .into(binding.profilePhoto)
    }

    override fun initArguments() {
        super.initArguments()
    }

    override fun initFragment() {
        super.initFragment()
    }

    override fun FragmentProfileBinding.initListeners() {
        linSignout.setOnClickListener {
            viewModel.obtainEvent(ProfileEvent.LogOut())
        }
        relOrders.setOnClickListener {
            router.navigateToMyOrders()
        }
        relPersonalData.setOnClickListener {
            router.navigateToPersonalData(null, PersonalDataFragment.Mode.PROFILE)
        }
        relCoTravellers.setOnClickListener {
            router.navigateToCoTravellers()
        }
        relChangePass.setOnClickListener {
            router.navigateToChangePass()
        }
        profilePhoto.setOnClickListener {
            router.navigateToPhotoActionDialog()
        }
        setFragmentResultListener(REQUEST_ADD_PHOTO) { requestKey, bundle ->
            addPhoto()
        }
    }

    override fun initData() {
        viewModel.obtainEvent(ProfileEvent.FetchProfile())
    }

    override fun initObservers() {
        viewModel.viewStates().observe(viewLifecycleOwner) { bindViewState(it) }
        viewModel.viewActions().observe(viewLifecycleOwner) { bindViewActions(it) }
    }

    private fun bindViewState(state: ProfileViewState) {
        when (state) {
            is ProfileViewState.LoggedOut -> router.reopenApp()
            is ProfileViewState.Error -> {
                onError(state.error)
                router.mainFragmentInstance?.onEndLoad()
            }
            is ProfileViewState.Loaded -> {
                binding.setProfileName(state.result)
                if (!state.result?.picture_file_name.isNullOrEmpty()) {
                    val picture = getImageUrl(state.result?.picture_file_name!!)
                    Glide.with(requireContext()).load(picture).apply(circleOprions)
                        .into(binding.profilePhoto)

                    updateGalleryListener(picture)
                }

                binding.relPersonalData.setOnClickListener {
                    state.result?.apply {
                        val person = PersonalDataModel(
                            1,
                            first_name + " " + last_name,
                            birthday,
                            email,
                            phone,
                            PersonalDataModel.Passport(
                                passport.number,
                                passport.issue_country,
                                passport.date_of_expiry
                            ),
                            picture_file_name
                        )

                        router.navigateToPersonalData(person, PersonalDataFragment.Mode.PROFILE)
                    }
                }
            }
            is ProfileViewState.LoadingData -> {
            }
            is ProfileViewState.LoadingPhoto -> {
                router.mainFragmentInstance?.onStartLoad()
            }
            is ProfileViewState.LoadedPhoto -> {
                Glide.with(requireContext()).load(state.photo).apply(circleOprions).into(binding.profilePhoto)
                state.photo?.let { updateGalleryListener(it) }
                router.mainFragmentInstance?.onEndLoad()
            }
        }
    }

    private fun bindViewActions(action: ProfileAction?) {
        when (action) {
            is ProfileAction.ShowSnackBar -> Snackbar.make(binding.root, getString(action.data), Snackbar.LENGTH_LONG)
                .show()
        }
    }


    private fun FragmentProfileBinding.setProfileName(result: ResponseProfile.Result?) {
        val firstName = result?.first_name
        val secondName = result?.last_name
        if (firstName == null && secondName != null) {
            tvProfileName.text = secondName
        } else if (firstName != null && secondName == null) {
            tvProfileName.text = firstName
        } else if (firstName != null && secondName != null) {
            tvProfileName.text = "$firstName $secondName"
        } else {
            tvProfileName.text = ""
        }
    }


    private fun addPhoto() {
        val readResult = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE,
        )
        val writeResult = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        )

        if (readResult == PackageManager.PERMISSION_GRANTED && writeResult == PackageManager.PERMISSION_GRANTED) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            imageLauncher.launch(intent)
        } else {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun updateGalleryListener(photo : String){
        setFragmentResultListener(REQUEST_OPEN_PHOTO) { requestKey, bundle ->
            Handler(Looper.getMainLooper()).postDelayed({
                router.navigatePhotoViewerFromProfile(listOf(photo))
            }, 150)
        }
    }
}
