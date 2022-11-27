package ru.vsibi.presentation.screens.profile.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseDialogFragment
import ru.vsibi.presentation.databinding.DialogAvatarChooserBinding

class ProfilePhotoActionDialog : BaseDialogFragment<DialogAvatarChooserBinding>(
    DialogAvatarChooserBinding::inflate,
    R.layout.dialog_avatar_chooser
) {

    companion object {
        const val REQUEST_ADD_PHOTO = "request_add_photo"
        const val REQUEST_OPEN_PHOTO = "request_open_photo"
    }

    override fun DialogAvatarChooserBinding.initListeners() {
        tvAdd.setOnClickListener {
            dismiss()
            setFragmentResult(REQUEST_ADD_PHOTO, Bundle())
        }
        tvOpen.setOnClickListener {
            dismiss()
            setFragmentResult(REQUEST_OPEN_PHOTO, Bundle())
        }
    }


}