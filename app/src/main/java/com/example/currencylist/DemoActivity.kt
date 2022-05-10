package com.example.currencylist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.databinding.ActivityDemoBinding
import com.example.currencylist.viewmodels.DemoViewModel
import com.example.currencylist.viewmodels.data.SortedOrder
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DemoActivity : AppCompatActivity(), IItemClickListener<CurrencyInfo> {
    private val viewModel: DemoViewModel by viewModels()
    private var updateCurrencyList: IUpdateCurrencyList<CurrencyInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = viewModel
        lifecycleScope.launchWhenResumed {
            viewModel.list.collect {
                updateCurrencyList?.updateList(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                Timber.d("UiState=$state")
                // For future requirement of ui
                when (state) {
                    DemoViewModel.UiState.InitState -> Unit
                    DemoViewModel.UiState.FailedState -> Unit
                    DemoViewModel.UiState.LoadedState -> Unit
                    DemoViewModel.UiState.LoadingState -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.currentSortedOrder.collect { sortedOrder ->
                Timber.d("sortedOrder=$sortedOrder")
                // For future requirement of ui
                when (sortedOrder) {
                    SortedOrder.Ascending -> Unit
                    SortedOrder.Descending -> Unit
                    SortedOrder.Unsorted -> Unit
                }
            }
        }
        updateCurrencyList = binding.fragmentContainer.getFragment<CurrencyListFragment>()
        updateCurrencyList?.setItemClickListener(this)
    }

    override fun onItemClick(data: CurrencyInfo) {
        Timber.d("onItemClick(data=$data)")
    }
}