package ru.vsibi.presentation.screens.tours.info

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import ru.vsibi.presentation.databinding.CellSpinnerTourBinding


class TourMenuAdapter(context: Context, resouceId: Int, list: List<TourCellModel>) :
    ArrayAdapter<TourCellModel>(
        context, resouceId, list
    ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onCreateRow(convertView, position)
    }

    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
        return onCreateRow(convertView, position)
    }

    private fun onCreateRow(convertView: View?, position: Int): View {
        val rowItem = getItem(position)
        var rowview = convertView
        if (rowview == null) {
            val binding = CellSpinnerTourBinding
                .inflate(LayoutInflater.from(context), null, false)
            binding.apply {
                tvCost.text = rowItem?.cost
                tvText.text = rowItem?.title
            }
            rowview = binding.root
        }
        return rowview
    }

}