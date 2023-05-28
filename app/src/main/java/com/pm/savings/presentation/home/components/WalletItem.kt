package com.pm.savings.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pm.savings.domain.wallet.model.Wallet
import com.pm.savings.presentation.ui.theme.headlineSmall
import com.pm.savings.presentation.ui.theme.small12
import com.pm.savings.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletItem(
    modifier: Modifier = Modifier,
    wallet: Wallet,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.width(160.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(wallet.color)
        ),
        onClick = onClick
    ) {
        Column {
            Column(
                modifier = Modifier.padding(
                    top = 12.dp,
                    start = 12.dp,
                    end = 12.dp,
                )
            ) {
                Text(
                    text = "Balance",
                    style = small12,
                    color = Color.White
                )
                Text(
                    text = "${wallet.sum} ${wallet.currency}",
                    style = headlineSmall,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(textColor)
                    .padding(
                        horizontal = 12.dp,
                        vertical = 8.dp
                    ),
            ) {
                Text(
                    text = wallet.title,
                    style = small12.copy(fontWeight = FontWeight.SemiBold, color = Color.White)
                )
            }
        }
    }
}


@Preview
@Composable
fun WalletItemPreview() {
    WalletItem(
        wallet = Wallet(
            id = 1,
            title = "Wallet",
            sum = 1000.0,
            currency = "USD",
            color = 0xFF5A56C2
        ),
        onClick = {}
    )
}