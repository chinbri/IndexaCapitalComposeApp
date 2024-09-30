package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinbri.indexacapitalapp.Greeting
import com.chinbri.indexacapitalapp.presentation.MainViewModel

@Composable
fun MainScreen(innerPadding: PaddingValues, mainViewModel: MainViewModel = hiltViewModel()) {

    val userInfo = mainViewModel.userInfo.collectAsState()

    Greeting(
        name = userInfo.value?.userName ?: "Loading...",
        modifier = Modifier.padding(innerPadding)
    )
}