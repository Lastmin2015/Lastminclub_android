package ru.vsibi.presentation.screens.tours.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.vsibi.domain.network.response.ResponseSearch
import ru.vsibi.helper.getDateDayMonth
import ru.vsibi.helper.getPlaceText
import ru.vsibi.presentation.databinding.CellHotelsBigBinding
import ru.vsibi.presentation.databinding.CellHotelsBinding
import ru.vsibi.presentation.helpers.Lastmin

class HotelsAdapter(private var itemClickListener: ((ResponseSearch.Result) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VT_SMALL = 0
        private const val VT_BIG = 1
    }

    private var isSmallSize = true
    private val hotels = mutableListOf<ResponseSearch.Result>()
    fun setupAdapter(hotels: List<ResponseSearch.Result>) {
        this.hotels.clear()
        this.hotels.addAll(hotels)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VT_BIG -> {
                val binding = CellHotelsBigBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                BigHotelsViewHolder(binding)
            }
            VT_SMALL -> {
                val binding = CellHotelsBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                SmallHotelsViewHolder(binding)
            }
            else -> error("Unknown View Type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VT_BIG -> {
                (holder as BigHotelsViewHolder).bind(position)
            }
            VT_SMALL -> {
                (holder as SmallHotelsViewHolder).bind(position)
            }
            else -> error("Unknown View Type for holder $holder")
        }

    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isSmallSize) VT_SMALL else VT_BIG
    }

    fun displayBigSize() {
        isSmallSize = false
        notifyDataSetChanged()
    }

    fun displaySmallSize() {
        isSmallSize = true
        notifyDataSetChanged()
    }

    inner class SmallHotelsViewHolder(private val binding: CellHotelsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = hotels[position]
            binding.apply {
                if (item.tour.hotel.hotel_images.isNotEmpty()) {
                    Glide.with(root.context)
                        .load(Lastmin.getImageUrl(item.tour.hotel.hotel_images[0].file_name))
                        .apply(Lastmin.listRequestOpts).into(image)
                }
                tvTitle.text = item.tour.hotel.name
                val regionName = item.tour.hotel.place.resort.region.region_name?.name
                val countryName = item.tour.hotel.place.resort.region.country.country_name?.name
                val resortName = item.tour.hotel.place.resort.resort_name.name
                tvDescription.text = getPlaceText(regionName, countryName, resortName)
                relCost.tvDate.text =
                    getDateDayMonth(item.tour.outbound_flight.date_from) + " - " + getDateDayMonth(
                        item.tour.return_flight.date_to
                    )
                relCost.tvCost.text = "€" + item.price
//                if (item.isFavorite) {
//                    relCost.ibFavorite.setImageResource(R.drawable.ic_icon_action_favorite_pink)
//                } else {
//                    relCost.ibFavorite.setImageResource(R.drawable.ic_icon_action_favorite_border)
//                }
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    inner class BigHotelsViewHolder(private val binding: CellHotelsBigBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = hotels[position]
            binding.apply {

                if (item.tour.hotel.hotel_images.isNotEmpty()) {
                    Glide.with(root.context)
                        .load(Lastmin.getImageUrl(item.tour.hotel.hotel_images[0].file_name))
                        .apply(Lastmin.listRequestOpts).into(image)
                }
                tvTitle.text = item.tour.hotel.name
                val regionName = item.tour.hotel.place.resort.region.region_name?.name ?: "Resort"
                val countryName = item.tour.hotel.place.resort.region.country.country_name?.name
                val resortName = item.tour.hotel.place.resort.resort_name.name
                tvDescription.text = getPlaceText(regionName, countryName, resortName)
                relCost.tvDate.text =
                    getDateDayMonth(item.tour.outbound_flight.date_from) + " - " + getDateDayMonth(
                        item.tour.return_flight.date_to
                    )
                relCost.tvCost.text = "€" + item.price
//                if (item.isFavorite) {
//                    relCost.ibFavorite.setImageResource(R.drawable.ic_icon_action_favorite_pink)
//                } else {
//                    relCost.ibFavorite.setImageResource(R.drawable.ic_icon_action_favorite_border)
//                }
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }
}