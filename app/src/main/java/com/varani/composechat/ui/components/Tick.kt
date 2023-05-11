package com.varani.composechat.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.varani.composechat.R

/**
 * Created by Ana Varani on 10/05/2023.
 */
@Composable
fun Tick(
    modifier: Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.delivered),
        contentDescription = stringResource(id = R.string.message_delivered),
        tint = colorResource(id = R.color.message_delivered),
        modifier = modifier
            .size(12.dp)
            .padding(end = 2.dp, bottom = 2.dp)
    )
}