package com.chinbri.indexacapitalapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinbri.indexacapitalapp.domain.model.AccountInfoResponse
import com.chinbri.indexacapitalapp.domain.usecase.GetAccountInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val getAccountInfoUseCase: GetAccountInfoUseCase): ViewModel() {

    private val _accountInfo = MutableStateFlow<AccountInfoResponse?>(null)
    val accountInfo: StateFlow<AccountInfoResponse?> = _accountInfo.asStateFlow()

    fun initialize(accountNumber: String?) {

        accountNumber?.let {
            viewModelScope.launch {
                _accountInfo.value = getAccountInfoUseCase(it)
            }
        }

    }


}
