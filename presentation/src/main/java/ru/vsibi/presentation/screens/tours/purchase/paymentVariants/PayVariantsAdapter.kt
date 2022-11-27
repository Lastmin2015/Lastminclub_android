package ru.vsibi.presentation.screens.tours.purchase.paymentVariants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.databinding.CellPayCardBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible
import ru.vsibi.presentation.models.CardModel
import ru.vsibi.presentation.models.PersonalDataModel

class PayVariantsAdapter(private var itemClickListener: ((CardModel, Boolean, Int) -> Unit)? = null) :
    RecyclerView.Adapter<PayVariantsAdapter.PayVariantsViewHolder>() {

    private val cards = mutableListOf<CardModel>()
    private var oldPosition = 0

    fun setupAdapter(cards: List<CardModel>) {
        this.cards.clear()
        this.cards.addAll(cards)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayVariantsViewHolder {
        val binding = CellPayCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return PayVariantsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PayVariantsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun removeAt(adapterPosition: Int) {
        cards.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun add(card : CardModel?) {
        if(card == null) return
        cards.add(card)
        notifyDataSetChanged()
    }

    fun select(isSelected: Boolean, position: Int) {
        if (cards.size > 0) {
            val item = cards[position]
            cards[oldPosition].isChecked = false
            item.isChecked = isSelected
            notifyItemChanged(oldPosition)
            notifyItemChanged(position)
            oldPosition = position
        }
    }


    inner class PayVariantsViewHolder(private val binding: CellPayCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = cards[position]
            binding.apply {
                tvCard.setText("${item.title} ${item.number}")
                ivIcon.setImageResource(item.iconRes)
                root.setOnClickListener {
                    itemClickListener?.invoke(item, !item.isChecked, position)
                }
                if(item.isChecked){
                    ivCheck.visible()
                }else{
                    ivCheck.gone()
                }
            }
        }
    }

}