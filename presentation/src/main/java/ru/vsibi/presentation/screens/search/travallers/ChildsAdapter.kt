package ru.vsibi.presentation.screens.search.travallers

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.vsibi.presentation.R
import ru.vsibi.presentation.databinding.CellChildsBinding

class ChildsAdapter : RecyclerView.Adapter<ChildsAdapter.ChildsViewHolder>() {

    private val childAges = mutableListOf<Int>()

    fun removeLast() {
        if (childAges.size > 0) {
            childAges.removeLast()
            notifyItemRemoved(childAges.size)
        }
    }

    fun add(age: Int) {
        childAges.add(age)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildsViewHolder {
        val binding = CellChildsBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildsViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return childAges.size
    }

    inner class ChildsViewHolder(private val binding: CellChildsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                tvChildNumber.text = ordinal(position + 1) + " " + itemView.context.getString(R.string.childs_age)
                tvChildAge.text = childAges[position].toString()
                linAge.setOnClickListener {
                    val popup = PopupMenu(itemView.context, it)
                    popup.menuInflater.inflate(R.menu.count_menu, popup.menu)
                    popup.setOnMenuItemClickListener { menuItem: MenuItem ->
                        tvChildAge.text = menuItem.title
                        childAges[position] = menuItem.title.toString().toInt()
                        return@setOnMenuItemClickListener true
                    }
                    popup.setOnDismissListener {}
                    popup.show()
                }
            }
        }
    }

    fun ordinal(i: Int): String? {
        val suffixes = arrayOf("th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th")
        return when (i % 100) {
            11, 12, 13 -> i.toString() + "th"
            else -> "" + i + suffixes[i % 10]
        }
    }


}