package ru.vsibi.presentation.screens.search.main

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.*
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.LayoutDatePickerBinding

class DateRangeDialog : BaseFragment<LayoutDatePickerBinding>(LayoutDatePickerBinding::inflate, R.layout.layout_date_picker) {

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    override fun LayoutDatePickerBinding.initViews() {
        startPickerFragment()
    }

    @SuppressLint("RestrictedApi")
    open fun startPickerFragment() {
        val dateSelector = CustomRangeSelector()

        val themeResId: Int = dateSelector.getDefaultThemeResId(requireContext())
        val pickerFragment = MaterialCalendar.newInstance(dateSelector, themeResId, CalendarConstraints.Builder().setOpenAt(
            System.currentTimeMillis()
        ).build())

        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.calendar, pickerFragment)
        fragmentTransaction.commitNow()
//        pickerFragment.addOnSelectionChangedListener(
//            object : OnSelectionChangedListener<S?>() {
//                override fun onSelectionChanged(selection: S) {
//                    updateHeader()
//                    confirmButton.setEnabled(getDateSelector().isSelectionComplete())
//                }
//
//                override fun onIncompleteSelectionChanged() {
//                    confirmButton.setEnabled(false)
//                }
//            })
    }


    override fun LayoutDatePickerBinding.initListeners() {
        btnCancel.setOnClickListener {
            popBack()
        }
        btnOk.setOnClickListener {
            popBack()
        }
        range3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                range5.isChecked = false
            }
        }
        range5.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                range3.isChecked = false
            }
        }
        durationWeek.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                duration2Week.isChecked = false
            }
        }
        duration2Week.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                durationWeek.isChecked = false
            }
        }
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

}