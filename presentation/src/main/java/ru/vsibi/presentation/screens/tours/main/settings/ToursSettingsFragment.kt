package ru.vsibi.presentation.screens.tours.main.settings

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResult
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentToursSettingsBinding


class ToursSettingsFragment : BaseFragment<FragmentToursSettingsBinding>(
    FragmentToursSettingsBinding::inflate,
    R.layout.fragment_tours_settings
) {

    companion object {
        const val TOURS_SETTINGS_KEY = "tours_settings_key"
        const val IS_SMALL = "is_small"
    }

    private var isSmallSize = false
    override fun FragmentToursSettingsBinding.initViews() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.view)

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun FragmentToursSettingsBinding.initListeners() {
        rbBig.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                isSmallSize = false
            }
        }
        rbSmall.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                isSmallSize = true
            }
        }
        btnSave.setOnClickListener {
            setFragmentResult(TOURS_SETTINGS_KEY, Bundle().apply {
                putBoolean(IS_SMALL, isSmallSize)
            })
            popBack()
        }
    }
}