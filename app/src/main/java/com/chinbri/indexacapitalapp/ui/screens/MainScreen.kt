package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chinbri.indexacapitalapp.R
import com.chinbri.indexacapitalapp.presentation.MainViewModel

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val userInfo = mainViewModel.userInfo.collectAsState()

    Column {
        Greeting(
            name = userInfo.value?.userName ?: "Loading...",
            modifier = Modifier.padding(innerPadding)
        )
        IndexaText(
            text = stringResource(id = R.string.accounts_title),
            modifier = Modifier.padding(innerPadding)
        )
        LazyColumn {

            userInfo.value?.accounts?.forEach { account ->
                item {
                    IndexaText(
                        text = account.accountNumber,
                        modifier = Modifier.clickable {
                            navController.navigate("accountScreen/${account.accountNumber}")
                        }
                    )
                }
            }

        }
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun IndexaText(
    modifier: Modifier = Modifier,
    text: String,
    fontStyle: FontStyle = FontStyle.Normal,
    fontSize: TextUnit = 16.sp,
    color: Color = Color.DarkGray,
    maxLines: Int = 1,
    expandable: Boolean = false
) {

    var expanded by remember { mutableStateOf(false) }

    Text(
        modifier = if (expandable) {
            modifier.clickable {
                expanded = !expanded
            }
        } else modifier,
        text = text,
        fontStyle = fontStyle,
        fontSize = fontSize,
        color = color,
        maxLines = if (expanded) Int.MAX_VALUE else maxLines
    )
}