package com.example.currencylist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencylist.data.CurrencyInfo
import com.example.currencylist.data.Resource
import com.example.currencylist.di.IODispatcher
import com.example.currencylist.domain.LoadDataUseCase
import com.example.currencylist.domain.SortedRuleUseCase
import com.example.currencylist.domain.SortingListUseCase
import com.example.currencylist.viewmodels.data.SortedOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val loadDataUseCase: LoadDataUseCase,
    private val sortingListUseCase: SortingListUseCase,
    private val sortedRuleUseCase: SortedRuleUseCase,
    @IODispatcher private val defaultDispatcher: CoroutineDispatcher
): ViewModel() {
    private val mutex = Mutex()
    private var _list: MutableStateFlow<List<CurrencyInfo>> = MutableStateFlow(listOf())
    val list: StateFlow<List<CurrencyInfo>> = _list

    private var _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.InitState)
    val uiState: StateFlow<UiState> = _uiState

    private var _currentSortedOrder: MutableStateFlow<SortedOrder> = MutableStateFlow(SortedOrder.Unsorted)
    var currentSortedOrder = _currentSortedOrder

    fun fetchData() {
        viewModelScope.launch(defaultDispatcher) {
            mutex.withLock {
                Timber.d("fetchData()")
                loadDataUseCase.execute().collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _uiState.value = UiState.FailedState
                        }
                        is Resource.Loading -> {
                            _uiState.value = UiState.LoadingState
                        }
                        is Resource.Success -> {
                            _currentSortedOrder.value = SortedOrder.Unsorted
                            _list.value = result.data ?: emptyList()
                            _uiState.value = UiState.LoadedState
                        }
                    }
                    cancel()
                }
            }
        }
    }

    fun sortData() {
        viewModelScope.launch(defaultDispatcher) {
            mutex.withLock {
                Timber.d("sortData(), currentSortedOrder=${currentSortedOrder.value}")
                _currentSortedOrder.value = sortedRuleUseCase.execute(_currentSortedOrder.value)
                _list.value = sortingListUseCase.execute(_list.value, _currentSortedOrder.value)
            }
        }
    }

    sealed class UiState {
        object InitState: UiState()
        object LoadingState: UiState()
        object LoadedState: UiState()
        object FailedState: UiState()
    }
}