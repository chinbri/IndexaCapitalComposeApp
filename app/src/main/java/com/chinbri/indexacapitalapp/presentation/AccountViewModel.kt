package com.chinbri.indexacapitalapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinbri.indexacapitalapp.domain.usecase.GetAccountInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val getAccountInfoUseCase: GetAccountInfoUseCase): ViewModel() {

    fun initialize(accountNumber: String?) {

        accountNumber?.let {
            viewModelScope.launch {
                val result = getAccountInfoUseCase(it)

                println("AAAAAAAAAA" + result)
            }
        }

    }


}
