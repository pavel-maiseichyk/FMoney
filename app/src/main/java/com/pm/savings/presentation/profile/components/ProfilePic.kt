package com.pm.savings.presentation.profile.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pm.savings.R

@Composable
fun ProfilePic(
    modifier: Modifier = Modifier,
    uri: Uri?,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(140.dp)
            .clip(CircleShape)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "profile picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            error = painterResource(id = R.drawable.user_pic)
        )
    }
}