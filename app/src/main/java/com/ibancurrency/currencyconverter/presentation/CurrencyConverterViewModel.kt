package com.ibancurrency.currencyconverter.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibancurrency.currencyconverter.domain.CurrencyConverterRepository
import com.ibancurrency.currencyconverter.domain.CurrencyItemEntity
import com.ibancurrency.currencyconverter.domain.CurrencyListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewModel @Inject constructor(
    private val currencyConverterRepository: CurrencyConverterRepository
): ViewModel(){
    private val _currencyListState: MutableLiveData<CurrencyListEntity> = MutableLiveData()
    val currencyListState: LiveData<CurrencyListEntity>
        get() = _currencyListState

    private val _currencyListLoadingState: MutableLiveData<Boolean> = MutableLiveData()
    val currencyListLoadingState: LiveData<Boolean>
     get() = _currencyListLoadingState

    fun initialise(){
        getCurrencyConversionList(null)
    }


    fun getCurrencyConversionList(conversionNumber: String?) {
        viewModelScope.launch {
            _currencyListLoadingState.postValue(true)
              currencyConverterRepository.getCurrencyConversionValues()
                .onSuccess {
                    /*
                        if there is some value in conversion number, happens during update
                        then once the result is back do a conversion
                     */
                    if(conversionNumber == null) {
                        _currencyListLoadingState.postValue(false)
                        _currencyListState.postValue(it)
                    }else{
                        convertCurrency(conversionNumber)
                    }

                }
                 .onFailure {
                     _currencyListLoadingState.postValue(false)
                     _currencyListState.postValue(
                         CurrencyListEntity(
                         isSuccess = false,
                         currencyItems = null,
                         timeStamp = null
                     )
                     )
                 }
        }

    }


     fun convertCurrency(conversionNumber: String) {
        if (currencyListState.value?.currencyItems!=null) {
            val updatedList = calculateConvertedValue(conversionNumber, currencyListState.value?.currencyItems)
            _currencyListLoadingState.postValue(false)
            _currencyListState.postValue(
                currencyListState.value!!.copy(
                currencyItems = updatedList
            ))
        }else {
            _currencyListLoadingState.postValue(false)
        }
    }

    private fun calculateConvertedValue(conversionNumber: String, currencyList: List<CurrencyItemEntity>?): List<CurrencyItemEntity>?{
        return if(currencyList!=null) {
            val toUpdateCurrencyList: List<CurrencyItemEntity> = currencyList
            toUpdateCurrencyList.forEach {
                it.calculatedValue = it.conversionValue * conversionNumber.toDouble()
            }
            toUpdateCurrencyList
        }else{
            null
        }

    }


}