package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinbri.indexacapitalapp.R
import com.chinbri.indexacapitalapp.domain.model.Instrument
import com.chinbri.indexacapitalapp.domain.model.Position
import com.chinbri.indexacapitalapp.presentation.AccountViewModel
import com.chinbri.indexacapitalapp.ui.theme.LightBlue

@Composable
fun AccountScreen(
    innerPadding: PaddingValues,
    accountNumber: String?,
    viewModel: AccountViewModel = hiltViewModel()
) {

    viewModel.initialize(accountNumber)

    val accountInfo = viewModel.accountInfo.collectAsState()

    Box(modifier = Modifier.padding(innerPadding)) {

        accountInfo.value?.let { accountInfoResponse ->

            accountInfoResponse.instrumentAccounts.forEach { instrumentAccount ->

                LazyColumn {
                    instrumentAccount.positions.forEach { position ->
                        item {
                            PositionItem(position)
                        }
                    }

                }
            }

        }
    }

}

@Composable
fun PositionItem(position: Position) {

    Column {

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightBlue)
                    .padding(16.dp)
            ) {
                Column {

                    Row {
                        IndexaText(
                            text = stringResource(R.string.isin) + ":"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IndexaText(
                            text = position.instrument?.isinCode ?: ""
                        )
                    }

                    Row {
                        IndexaText(
                            text = stringResource(R.string.name) + ":"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IndexaText(
                            text = position.instrument?.name ?: ""
                        )
                    }

                    Row {
                        IndexaText(
                            text = stringResource(R.string.description) + ":"
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        IndexaText(
                            text = position.instrument?.description ?: "",
                            maxLines = 2
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row {
                        IndexaText(
                            text = stringResource(R.string.amount) + ":"
                        )
                        IndexaText(
                            text = position.amount.toString()
                        )
                    }


                }

            }

        }

    }

}

@Preview
@Composable
fun PositionItemPreview() {
    PositionItem(
        Position(
            amount = 100.0,
            costAmount = 140.0,
            date = "2021-01-01",
            instrument = Instrument(
                assetClassDescription = "sadf",
                description = "asdf",
                isinCode = "432435",
                name = "global vanguard"
            )
        )
    )
}