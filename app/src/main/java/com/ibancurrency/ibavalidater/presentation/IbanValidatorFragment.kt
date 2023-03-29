package com.ibancurrency.ibavalidater.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ibancurrency.R
import com.ibancurrency.databinding.FragmentIbanvalidatorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IbanValidatorFragment : Fragment() {

    private var _binding: FragmentIbanvalidatorBinding? = null
    private val viewModel: IbanValidatorViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIbanvalidatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intialiseObservers()
        binding.buttonValidate.setOnClickListener {
            //Setting to Invisible instead of GONE to avoid buttons shifting up and down
            binding.textViewValidationResult.visibility = View.INVISIBLE
            viewModel.validateIban(binding.editTextIban.text.toString())
        }
    }

    fun intialiseObservers(){
        viewModel.validationState.observe(viewLifecycleOwner) {
            binding.textViewValidationResult.visibility = View.VISIBLE
            if (it.isValid) {
                binding.textViewValidationResult.text = it.validationMessage
                binding.textViewValidationResult.setTextColor(resources.getColor(R.color.green, null))
                Toast.makeText(activity, getString(R.string.iban_validation_success), Toast.LENGTH_LONG).show()
            } else {
                binding.textViewValidationResult.text = it.validationMessage
                binding.textViewValidationResult.setTextColor(resources.getColor(R.color.red, null))
                Toast.makeText(activity, getString(R.string.iban_validtion_failed), Toast.LENGTH_LONG).show()
            }
        }

        viewModel.isRequestingValidation.observe(viewLifecycleOwner) {
            if(it) {
                binding.buttonValidate.setText(getString(R.string.validating))
                binding.buttonValidate.isEnabled = false
            }else{
                binding.buttonValidate.setText(getString(R.string.validate))
                binding.buttonValidate.isEnabled = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}