package com.ibancurrency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ibancurrency.databinding.FragmentSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class SelectionFragment : Fragment() {

    private var _binding: FragmentSelectionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonIbanvalidator.setOnClickListener {
            findNavController().navigate(R.id.action_SelectionFragment_to_IbanValidator)
        }
        binding.buttonCurrencyconverter.setOnClickListener {
            findNavController().navigate(R.id.action_SelectionFragment_to_CurrencyConverter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}