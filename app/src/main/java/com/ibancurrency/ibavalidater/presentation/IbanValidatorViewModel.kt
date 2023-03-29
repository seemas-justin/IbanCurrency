package com.ibancurrency.ibavalidater.presentation

import androidx.lifecycle.*
import com.ibancurrency.ibavalidater.domain.IbanValidatorRepository
import com.ibancurrency.ibavalidater.domain.ValidationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IbanValidatorViewModel

@Inject
constructor(
    val ibanValidatorRepository: IbanValidatorRepository
): ViewModel() {
    private val _validationState: MutableLiveData<ValidationEntity> = MutableLiveData()
     val validationState: LiveData<ValidationEntity>
    get() = _validationState

    private val _isRequestingValidation: MutableLiveData<Boolean> = MutableLiveData()
    val isRequestingValidation: LiveData<Boolean>
        get() = _isRequestingValidation


    fun validateIban(iban: String){
        if (isLocalValidationSuccess(iban)){
            viewModelScope.launch {
                _isRequestingValidation.postValue(true)
                ibanValidatorRepository.validateIban(iban)
                    .onSuccess {
                        _isRequestingValidation.postValue(false)
                        _validationState.postValue(it?.let { it1 -> ValidationEntity.mapFromModel(it1) })
                }
                    .onFailure {
                        _isRequestingValidation.postValue(false)
                        _validationState.postValue(
                            ValidationEntity(
                                isValid = false,
                                validationMessage = "Some exception occurred - Validation failed"
                            )
                        )
                    }
            }
        }else{
            _validationState.postValue(
                ValidationEntity(
                    isValid = false,
                    validationMessage = "Iban number should be more than 5 digits"
            )
            )

        }
    }

    fun isLocalValidationSuccess(iban: String): Boolean {
        return iban.length > 5
    }

}

