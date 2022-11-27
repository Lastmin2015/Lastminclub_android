package ru.vsibi.presentation.screens.flights.exclude

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.databinding.CellExcludeCountriesBinding
import ru.vsibi.presentation.models.CountryModel

class ExcludeCountriesAdapter(private var itemClickListener: ((CountryModel) -> Unit)? = null) :
    RecyclerView.Adapter<ExcludeCountriesAdapter.ExcludeCountriesViewHolder>() {

    private val countries = mutableListOf<CountryModel>()

    fun setupAdapter(countries: List<CountryModel>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExcludeCountriesViewHolder {
        val binding = CellExcludeCountriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ExcludeCountriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExcludeCountriesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun removeAt(adapterPosition: Int) {
        countries.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun add(country : CountryModel?) {
        if(country == null) return
        countries.add(country)
        notifyDataSetChanged()
    }

    inner class ExcludeCountriesViewHolder(private val binding: CellExcludeCountriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = countries[position]
            binding.apply {
                tvTitle.text = item.title
                root.setOnClickListener {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

}