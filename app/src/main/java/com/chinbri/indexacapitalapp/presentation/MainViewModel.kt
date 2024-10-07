package com.chinbri.indexacapitalapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinbri.indexacapitalapp.domain.model.UserInfoResponse
import com.chinbri.indexacapitalapp.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val getUserInfoUseCase: GetUserInfoUseCase): ViewModel(){


    private val _userInfo = MutableStateFlow<UserInfoResponse?>(null)
    val userInfo: StateFlow<UserInfoResponse?> = _userInfo.asStateFlow()

    init {

        viewModelScope.launch {
            _userInfo.value = getUserInfoUseCase(Unit)
        }

    }

}