package ru.vsibi.presentation.screens.profile.coTravellers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.vsibi.presentation.R
import ru.vsibi.presentation.databinding.CellCoTravellersBinding
import ru.vsibi.presentation.databinding.CellHotelsBinding
import ru.vsibi.presentation.helpers.Lastmin
import ru.vsibi.presentation.models.PersonalDataModel

class CoTravellersAdapter(private var itemClickListener: ((PersonalDataModel) -> Unit)? = null) :
    RecyclerView.Adapter<CoTravellersAdapter.CoTravellersViewHolder>() {

    private val travellers = mutableListOf<PersonalDataModel>()

    fun setupAdapter(travellers: List<PersonalDataModel>) {
        this.travellers.clear()
        this.travellers.addAll(travellers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoTravellersViewHolder {
        val binding = CellCoTravellersBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CoTravellersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoTravellersViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return travellers.size
    }

    fun removeAt(adapterPosition: Int) {
        travellers.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun add(person : PersonalDataModel?) {
        if(person == null) return
        travellers.add(person)
        notifyDataSetChanged()
    }

    inner class CoTravellersViewHolder(private val binding: CellCoTravellersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = travellers[position]
            binding.apply {
                tvName.text = item.name
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

}