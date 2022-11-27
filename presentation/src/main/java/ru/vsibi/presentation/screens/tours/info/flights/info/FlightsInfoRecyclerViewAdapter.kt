package ru.vsibi.presentation.screens.tours.info.flights.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.vsibi.presentation.R
import ru.vsibi.presentation.databinding.*
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.screens.tours.info.flights.info.models.*
import ru.vsibi.presentation.screens.tours.main.HotelsAdapter

class FlightsInfoRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VT_ITEM = 0
        private const val VT_DESCRIPTION = 1
        private const val VT_ARRIVAL = 2
        private const val VT_CONNECTION = 3
    }

    private val flightsInfo = mutableListOf<FlightInfo>()

    fun setupAdapter(flightInfo: List<FlightInfo>) {
        this.flightsInfo.clear()
        this.flightsInfo.addAll(flightInfo)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            VT_ITEM -> {
                val binding = CellFlightItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return FlightsItemViewHolder(binding)
            }
            VT_DESCRIPTION -> {
                val binding = CellFlightDescriptionBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return FlightsDescriptionViewHolder(binding)
            }
            VT_ARRIVAL -> {
                val binding = CellFlightArrivalBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return FlightsArrivalViewHolder(binding)
            }
            VT_CONNECTION -> {
                val binding = CellFlightConnectionBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return FlightsConnectionViewHolder(binding)
            }
            else -> error("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VT_ITEM -> {
                (holder as FlightsItemViewHolder).bind(position)
            }
            VT_DESCRIPTION -> {
                (holder as FlightsDescriptionViewHolder).bind(position)
            }
            VT_ARRIVAL -> {
                (holder as FlightsArrivalViewHolder).bind(position)
            }
            VT_CONNECTION -> {
                (holder as FlightsConnectionViewHolder).bind(position)
            }
            else -> error("Unknown View Type for holder $holder")
        }
    }

    override fun getItemCount() = flightsInfo.size


    override fun getItemViewType(position: Int) = when (flightsInfo[position]) {
        is FlightArrival -> VT_ARRIVAL
        is FlightConnectionItem -> VT_CONNECTION
        is FlightDescription -> VT_DESCRIPTION
        is FlightItem -> VT_ITEM
        else -> error("unknown view type for position $position")
    }

    inner class FlightsItemViewHolder(val binding: CellFlightItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = flightsInfo[position] as FlightItem
            binding.apply {
                tvTime.text = item.time
                tvCity.text = item.city
                tvAirport.text = item.airport
            }
        }
    }

    inner class FlightsConnectionViewHolder(val binding: CellFlightConnectionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = flightsInfo[position] as FlightConnectionItem
            binding.apply {
                tvFlightConnection.text = "${root.context.getString(R.string.flight_connection)} ${item.duration}"
            }
        }
    }

    inner class FlightsDescriptionViewHolder(val binding: CellFlightDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = flightsInfo[position] as FlightDescription
            binding.apply {
                tvDuration.text = item.duration
                tvClass.text = item.`class`
                tvCompany.text = item.companyName
                if(item.isChecked){
                    linInfo.visible()
                    ivMenu.setImageResource(R.drawable.ic_baseline_arrow_drop_up_24)
                }else{
                    linInfo.gone()
                    ivMenu.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24_black)
                }
                root.setOnClickListener {
                    item.isChecked = !item.isChecked
                    notifyItemChanged(position)
                }
                flightNo.tvStart.setText(R.string.flight_no)
                flightNo.tvEnd.setText("FR 2145")
                seatPitch.tvStart.setText(R.string.seat_pitch)
                seatPitch.tvEnd.setText("76 cm")
                seatWidth.tvStart.setText(R.string.seat_width)
                seatWidth.tvEnd.setText("43 cm")
                seatRecline.tvStart.setText(R.string.seat_recline)
                seatRecline.tvEnd.setText("7 cm")
                seatAudioVideo.tvStart.setText(R.string.seat_audio_video)
                seatAudioVideo.tvEnd.setText("No")
                seatPower.tvStart.setText(R.string.seat_power)
                seatPower.tvEnd.setText("Yes")
                seatWifi.tvStart.setText(R.string.seat_wifi)
                seatWifi.tvEnd.setText("No")
            }
        }
    }

    inner class FlightsArrivalViewHolder(val binding: CellFlightArrivalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = flightsInfo[position] as FlightArrival
            binding.apply {
                tvArrival.text = item.arrivalDesc
                tvTime.text = item.time
            }
        }
    }

}
