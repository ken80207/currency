package com.example.currencylist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.IItemClickListener
import com.example.currencylist.R
import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.databinding.ListItemCurrencyInfoBinding

class CurrencyListAdapter(
    private val itemClickListener: IItemClickListener<CurrencyInfo>
) : ListAdapter<CurrencyInfo, CurrencyListAdapter.ViewHolder>(
    CurrencyInfoDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_currency_info,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            data = getItem(position)
            clickListener = itemClickListener
        }
    }

    class ViewHolder(
        val binding: ListItemCurrencyInfoBinding
    ) : RecyclerView.ViewHolder(binding.root)
}

private class CurrencyInfoDiffCallback : DiffUtil.ItemCallback<CurrencyInfo>() {

    override fun areItemsTheSame(
        oldItem: CurrencyInfo,
        newItem: CurrencyInfo
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CurrencyInfo,
        newItem: CurrencyInfo
    ): Boolean {
        return oldItem.name == newItem.name
    }
}
