package ru.vsibi.presentation.screens.search.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import com.google.android.material.datepicker.*
import com.google.android.material.internal.ManufacturerUtils
import com.google.android.material.internal.ViewUtils
import com.google.android.material.textfield.TextInputLayout
import ru.vsibi.presentation.R

//import com.google.android.material.datepicker.UtcDates

@SuppressLint("RestrictedApi")
class CustomRangeSelector : RangeDateSelector() {

    companion object {
        const val DAY_3_AGO = 259200000
        const val DAY_5_AGO = 432000000
    }

    private var selectedItem: Long? = null
    private var mode: MaterialDatePicker.RadioMode = MaterialDatePicker.RadioMode.DAYS_3
    private var notifyListener: ((Long) -> Unit?)? = null

    override fun select(selection: Long) {
        selectedItem = selection
        var dayAgoMillis = 0
        if (mode == MaterialDatePicker.RadioMode.DAYS_3) {
            dayAgoMillis = DAY_3_AGO
        } else if (mode == MaterialDatePicker.RadioMode.DAYS_5) {
            dayAgoMillis = DAY_5_AGO
        }
        super.select(selectedItem!! - dayAgoMillis)
        super.select(selectedItem!! + dayAgoMillis)
    }

    override fun getSelectedDays(): MutableCollection<Long> {
        val selections = super.getSelectedDays()
        selectedItem?.let {
            selections.add(it)
        }
        return selections
    }

    fun switchRadioMode(mode: MaterialDatePicker.RadioMode?) {
        if (mode != null) {
            this.mode = mode
        }
        selectedItem?.let {
            select(it)
            notifyListener?.invoke(it)
        }
    }

    override fun onCreateTextInputView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?,
        constraints: CalendarConstraints,
        listener: OnSelectionChangedListener<Pair<Long, Long>>
    ): View {
        val root: View = layoutInflater.inflate(R.layout.mtrl_picker_text_input_date, viewGroup, false)
        val dateTextInput: TextInputLayout = root.findViewById(R.id.mtrl_picker_text_input_date)
        val dateEditText = dateTextInput.editText
        if (ManufacturerUtils.isDateInputKeyboardMissingSeparatorCharacters()) {
            // Using the URI variation places the '/' and '.' in more prominent positions
            dateEditText!!.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_URI
        }
        val format = UtcDatesCopy.textInputFormat
        val formatHint = UtcDatesCopy.getTextInputHint(root.resources, format)
        dateTextInput.placeholderText = formatHint
        if (selectedItem != null) {
            dateEditText!!.setText(format.format(selectedItem))
        }
        dateEditText!!.addTextChangedListener(
            object : DateFormatTextWatcher(formatHint, format, dateTextInput, constraints) {
                override fun onValidDate(day: Long?) {
                    if (day == null) {
                        clearSelection()
                    } else {
                        select(day)
                        notifyListener?.invoke(day)
                    }
                    listener.onSelectionChanged(selection)
                }

                override fun onInvalidDate() {
                    listener.onIncompleteSelectionChanged()
                }
            })
        ViewUtils.requestFocusAndShowKeyboard(dateEditText)
        return root
    }

    private fun clearSelection() {
        selectedItem = null
        super.setSelection(Pair(null, null))
    }

    override fun getSelectionDisplayString(context: Context): String {
        val res = context.resources
        if (selectedItem == null) {
            return res.getString(com.google.android.material.R.string.mtrl_picker_date_header_unselected)
        }
        val startString = DateStrings.getYearMonthDay(selectedItem!!)
        return res.getString(com.google.android.material.R.string.mtrl_picker_date_header_selected, startString)
    }

    override fun getDefaultTitleResId(): Int {
        return R.string.departure_date
    }

    fun setupNotifyListener(unit: (Long) -> Unit) {
        this.notifyListener = unit
    }
//
//    private fun create3daysAgo(): Collection<Pair<Long?, Long?>?> {
//        if (selectedItem == null) return emptyList()
//        val agodays = arrayListOf<Pair<Long?, Long?>?>()
//        agodays.add(Pair(selectedItem!!, selectedItem!! - 259200000))
//        agodays.add(Pair(selectedItem!!, selectedItem!! + 259200000))
//        return agodays
//    }
//
//    override fun getSelectedRanges(): Collection<Pair<Long?, Long?>?> {
//        val ranges = ArrayList<Pair<Long?, Long?>?>()
//        ranges.addAll(create3daysAgo())
//        return ranges
//    }
//
//    override fun getSelection(): Pair<Long, Long> {
//        return Pair(
//            selectedItem!! - 259200000,
//            selectedItem!! + 259200000
//        )
//    }
}