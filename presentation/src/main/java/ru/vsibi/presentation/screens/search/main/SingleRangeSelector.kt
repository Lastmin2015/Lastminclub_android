package ru.vsibi.presentation.screens.search.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
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

class SingleRangeSelector() : DateSelector<Long> {

    private val selectedItem: Long? = null

    constructor(parcel: Parcel) : this() {}

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun getSelection(): Long? {
        TODO("Not yet implemented")
    }

    override fun isSelectionComplete(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSelection(selection: Long) {
        TODO("Not yet implemented")
    }

    override fun select(selection: Long) {
        TODO("Not yet implemented")
    }

    override fun getSelectedDays(): MutableCollection<Long> {
        TODO("Not yet implemented")
    }

    override fun getSelectedRanges(): MutableCollection<Pair<Long, Long>> {
        TODO("Not yet implemented")
    }

    override fun getSelectionDisplayString(context: Context?): String {
        TODO("Not yet implemented")
    }

    override fun getDefaultTitleResId(): Int {
        TODO("Not yet implemented")
    }

    override fun getDefaultThemeResId(context: Context?): Int {
        TODO("Not yet implemented")
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateTextInputView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?,
        constraints: CalendarConstraints,
        listener: OnSelectionChangedListener<Long>
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

//        dateEditText!!.addTextChangedListener(
//            object : DateFormatTextWatcher(formatHint, format, dateTextInput, constraints) {
//                fun onValidDate(day: Long?) {
//                    if (day == null) {
//                        clearSelection()
//                    } else {
//                        select(day)
//                    }
//                    listener.onSelectionChanged(selection!!)
//                }
//
//                fun onInvalidDate() {
//                    listener.onIncompleteSelectionChanged()
//                }
//            })

        ViewUtils.requestFocusAndShowKeyboard(dateEditText!!)

        return root
    }

    companion object CREATOR : Parcelable.Creator<SingleRangeSelector> {
        override fun createFromParcel(parcel: Parcel): SingleRangeSelector {
            return SingleRangeSelector(parcel)
        }

        override fun newArray(size: Int): Array<SingleRangeSelector?> {
            return arrayOfNulls(size)
        }
    }
}