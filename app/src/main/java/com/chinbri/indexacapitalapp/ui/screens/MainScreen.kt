package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.chinbri.indexacapitalapp.R
import com.chinbri.indexacapitalapp.domain.model.Account
import com.chinbri.indexacapitalapp.presentation.MainViewModel
import com.chinbri.indexacapitalapp.ui.theme.DarkBlue
import com.chinbri.indexacapitalapp.ui.theme.LightBlue
import com.chinbri.indexacapitalapp.ui.theme.LighterBlue

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val userInfo = mainViewModel.userInfo.collectAsState()

    Column(modifier = Modifier
        .fillMaxHeight()
        .background(color = LighterBlue)
        .padding(20.dp)) {
        IndexaText(
            modifier = Modifier.padding(innerPadding),
            text = stringResource(R.string.wellcome) + " ${userInfo.value?.userName}!",
            fontSize = 20.sp
        )
        IndexaText(
            modifier = Modifier.padding(innerPadding),
            text = stringResource(id = R.string.accounts_title),
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        LazyColumn {

            userInfo.value?.accounts?.forEach { account ->
                item {
                    IndexaMainScreenItem(account, navController)
                }
            }

        }
    }

}

@Composable
private fun IndexaMainScreenItem(
    account: Account,
    navController: NavHostController
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue)
                .clickable {
                    navController.navigate("accountScreen/${account.accountNumber}")
                }
                .padding(16.dp)
        ) {
            IndexaText(
                text = "${account.accountNumber}: "
                        + stringResource(
                    if (account.type == "pension") {
                        R.string.account_pension
                    } else {
                        R.string.account_investment
                    }
                ),
                color = LightBlue
            )
        }
    }
}

@Composable
@Preview
fun PreviewIndexaMainScreenItem() {
    IndexaMainScreenItem(
        account = Account(
            accountNumber = "123456",
            type = "pension"
        ),
        navController = NavHostController(LocalContext.current)
    )
}

