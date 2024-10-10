package com.chinbri.indexacapitalapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.chinbri.indexacapitalapp.R
import com.chinbri.indexacapitalapp.domain.model.Instrument
import com.chinbri.indexacapitalapp.domain.model.Position
import com.chinbri.indexacapitalapp.presentation.AccountViewModel
import com.chinbri.indexacapitalapp.ui.theme.DarkBlue
import com.chinbri.indexacapitalapp.ui.theme.LightBlue
import java.util.Locale

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

    var showDescription: Boolean by remember { mutableStateOf(false) }

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

                    PositionLabelValue(
                        label = stringResource(R.string.isin),
                        value = position.instrument?.isinCode ?: ""
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        PositionLabelValue(
                            label = stringResource(R.string.name),
                            value = position.instrument?.name ?: ""
                        )
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = DarkBlue,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable {
                                    showDescription = showDescription.not()
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IndexaText(
                            text = stringResource(R.string.amount) + ":",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        IndexaText(
                            fontWeight = FontWeight.Bold,
                            text = String.format(
                                Locale.getDefault(),
                                "%.1f",
                                position.amount
                            ) + " €",
                            fontSize = 20.sp,
                        )
                    }

                }


                if (showDescription) {

                    Dialog(
                        onDismissRequest = { showDescription = false }
                    ) {

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 40.dp),
                        ) {
                            Box(modifier = Modifier.background(color = Color.White).padding(32.dp)){

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                                    IndexaText(
//                                        fontWeight = FontWeight.Bold,
//                                        text = position.instrument?.description ?: "",
//                                        maxLines = Int.MAX_VALUE,
//                                    )
                                    HyperlinkText(position.instrument?.description ?: "")

                                    Spacer(modifier = Modifier.height(16.dp))

                                    IndexaButton(stringResource(R.string.close)) {
                                        showDescription = false
                                    }
                                }

                            }

                        }


                    }


                }

            }

        }

    }


}

@Composable
private fun PositionLabelValue(
    label: String,
    value: String,
    maxLines: Int = 1,
) {
    Column {
        IndexaText(
            text = "$label:",
        )
        Spacer(modifier = Modifier.height(4.dp))
        IndexaText(
            fontWeight = FontWeight.Bold,
            text = value,
            maxLines = maxLines,
            expandable = maxLines > 1,
        )
        Spacer(modifier = Modifier.height(16.dp))
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