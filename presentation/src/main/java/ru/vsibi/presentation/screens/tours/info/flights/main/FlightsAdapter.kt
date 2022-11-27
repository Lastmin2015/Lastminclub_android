package ru.vsibi.presentation.screens.tours.info.flights.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.databinding.CellFlightsBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.invis
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.models.flight.FlightResult
import java.text.SimpleDateFormat
import java.util.*

class FlightsAdapter(private var itemClickListener: ((FlightResult) -> Unit)? = null) :
    RecyclerView.Adapter<FlightsAdapter.FlightsViewHolder>() {

    private val flights = mutableListOf<FlightResult>()
    private val timeFormatter = SimpleDateFormat("HH:mm")
    private val dateFormatter = SimpleDateFormat("EEE, dd MMMM yyyy")
    fun setupAdapter(flights: List<FlightResult>) {
        this.flights.clear()
        this.flights.addAll(flights)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightsViewHolder {
        val binding = CellFlightsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return flights.size
    }

    inner class FlightsViewHolder(private val binding: CellFlightsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = flights[position]
            binding.apply {
                tvCityFrom.text = item.city_from
                tvCityTo.text = item.city_to
                tvDuration.text = timeFormatter.format(Date(item.duration.total * 1000))
                tvTimeStart.text = timeFormatter.format(Date(item.date_from * 1000))
                tvTimeEnd.text = timeFormatter.format(Date(item.date_to * 1000))
                tvDate.text = dateFormatter.format(Date(item.date_from * 1000))
                tvFromCode.text = item.city_code_from
                tvToCode.text = item.city_code_to

                if (item.stops.size == 0) {
                    linStops.visible()
                    ivStop1.visible()
                    ivStopInter1.gone()
                    ivStop2.gone()
                    ivStopInter2.gone()
                    ivStop3.gone()
                    tvStops.setText("Direct")
                    tvStopsCodes.gone()
                }else if(item.stops.size == 1){
                    linStops.visible()
                    ivStop1.visible()
                    ivStopInter1.visible()
                    ivStop2.visible()
                    ivStopInter2.gone()
                    ivStop3.gone()
                    tvStops.setText("Stops: 1")
                    tvStopsCodes.visible()
                    tvStopsCodes.text = item.stops[0].city_code_from
                }

                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

}