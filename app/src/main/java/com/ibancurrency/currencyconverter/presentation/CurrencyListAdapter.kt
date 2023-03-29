package com.ibancurrency.currencyconverter.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibancurrency.databinding.ListItemCurrencyBinding
import com.ibancurrency.currencyconverter.domain.CurrencyItemEntity
import com.ibancurrency.core.toCurrencyDecimals




class CurrencyListAdapter constructor(var currencyList: List<CurrencyItemEntity>) :
    RecyclerView.Adapter<CurrencyListAdapter.CurrencyListViewHolder>()
{

    fun updateList(currencyList: List<CurrencyItemEntity>){
        this.currencyList = currencyList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyListViewHolder {
        val listItemCurrencyBinding = ListItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyListViewHolder(listItemCurrencyBinding)
    }

    override fun getItemCount() = currencyList.size

    override fun onBindViewHolder(holder: CurrencyListViewHolder, position: Int) {
        holder.bind(currencyList[position])
    }

    inner class CurrencyListViewHolder constructor(private val binding: ListItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currencyItemEntity: CurrencyItemEntity) {
            binding.textviewCurrencysymbol.text = currencyItemEntity.symbol
            if(currencyItemEntity.calculatedValue == 0.0) {
                binding.textviewCalculatedvalue.visibility = View.GONE
            }else{
                binding.textviewCalculatedvalue.visibility = View.VISIBLE
                binding.textviewCalculatedvalue.text = currencyItemEntity.calculatedValue.toCurrencyDecimals()
            }
            binding.textviewConversion.text =  "I KD = ${currencyItemEntity.conversionValue.toCurrencyDecimals()}"
        }
    }

}