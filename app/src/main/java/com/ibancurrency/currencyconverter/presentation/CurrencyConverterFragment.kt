package com.ibancurrency.currencyconverter.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibancurrency.databinding.FragmentCurrencyConverterBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class CurrencyConverterFragment : Fragment(), LifecycleObserver {

    private var _binding: FragmentCurrencyConverterBinding? = null
    private val viewModel: CurrencyConverterViewModel by viewModels()
    private var isFirstTimeLoad = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCurrencyConverterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConvert.setOnClickListener {
            val numberToConvert = binding.edittextCurrency.text.toString()
            viewModel.convertCurrency(numberToConvert)

        }
        binding.buttonRefreshRate.setOnClickListener{
            /*
                Get the number to be converted and
                if it has value then request it with that number else with null
             */
            val numberToConvert = binding.edittextCurrency.text.toString()
            viewModel.getCurrencyConversionList(
                numberToConvert.ifEmpty { null }
            )

        }
        initialiseObeservers()
        viewModel.initialise()
    }

    fun initialiseObeservers(){

        viewModel.currencyListState.observe(viewLifecycleOwner) {
            if((it.isSuccess == true) && (it.currencyItems?.isNotEmpty() == true)){
                if(isFirstTimeLoad){
                    binding.recycularviewCurrencyList.layoutManager = LinearLayoutManager(activity)
                    binding.recycularviewCurrencyList.adapter = CurrencyListAdapter(currencyList = it.currencyItems)
                    isFirstTimeLoad = false
                }else{
                    (binding.recycularviewCurrencyList.adapter as CurrencyListAdapter).updateList(currencyList = it.currencyItems)
                }

            }
        }

        viewModel.currencyListLoadingState.observe(viewLifecycleOwner) {
            if(isFirstTimeLoad){
                if(it){
                    binding.content.visibility = View.GONE
                    binding.mainProgressLayout.root.visibility = View.VISIBLE
                }else{
                    binding.content.visibility = View.VISIBLE
                    binding.mainProgressLayout.root.visibility = View.GONE
                }
            }else{
                if(it){
                    binding.recycularviewCurrencyList.visibility = View.GONE
                    binding.progressViewLayout.root.visibility = View.VISIBLE
                }else{
                    binding.recycularviewCurrencyList.visibility = View.VISIBLE
                    binding.progressViewLayout.root.visibility = View.GONE
                }
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}