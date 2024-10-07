package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinbri.indexacapitalapp.presentation.AccountViewModel

@Composable
fun AccountScreen(
    innerPadding: PaddingValues,
    accountNumber: String?,
    viewModel: AccountViewModel = hiltViewModel()
) {

    viewModel.initialize(accountNumber)

    Box(modifier = Modifier.padding(innerPadding)) {
        Text(text = accountNumber ?: "No account number provided")
    }

}