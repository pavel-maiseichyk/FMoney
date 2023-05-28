package com.pm.savings.presentation.home.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pm.savings.R
import com.pm.savings.presentation.core.Constants
import com.pm.savings.presentation.ui.theme.headlineSmall
import com.pm.savings.presentation.ui.theme.small12

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    uri: Uri?,
    username: String
) {
    Row(
        modifier = modifier.height(Constants.TOP_BAR_HEIGHT),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = uri,
                contentDescription = "profile picture",
                error = painterResource(id = R.drawable.user_pic)
            )
        }
        Column {
            Text(text = "Good Morning!", style = small12)
            Text(text = username, style = headlineSmall)
        }
    }
}