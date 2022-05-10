package com.example.currencylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.currencylist.adapters.CurrencyListAdapter
import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.databinding.FragmentCurrencyListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CurrencyListFragment: Fragment(), IUpdateCurrencyList<CurrencyInfo>, IItemClickListener<CurrencyInfo> {
    private lateinit var binding: FragmentCurrencyListBinding
    private var itemClickListener: IItemClickListener<CurrencyInfo>? = null
    private lateinit var currencyListAdapter: CurrencyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this.viewLifecycleOwner
        setupAdapter()
    }

    override fun updateList(list: List<CurrencyInfo>) {
        currencyListAdapter.submitList(list)
    }

    override fun setItemClickListener(listener: IItemClickListener<CurrencyInfo>) {
        itemClickListener = listener
    }

    override fun onItemClick(data: CurrencyInfo) {
        Timber.d("onItemClick(data=${data})")
        itemClickListener?.onItemClick(data)
    }

    private fun setupAdapter() {
        currencyListAdapter = CurrencyListAdapter(this)
        binding.recycler.apply {
            adapter = currencyListAdapter
            setHasFixedSize(true)
            itemAnimator = null
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayout.VERTICAL
                )
            )
        }
    }
}