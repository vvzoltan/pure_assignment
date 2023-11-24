package com.vvz.pure.test.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vvz.pure.test.R
import com.vvz.pure.test.domain.models.PureError


@Composable
internal fun ErrorView(error: PureError,
                       onRetry: () -> Unit,
                       modifier: Modifier = Modifier) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center,
           modifier = modifier.fillMaxSize()) {

        val title = when (error) {
            is PureError.Network.Connection -> stringResource(id = R.string.error_network)
            is PureError.Network.Coding     -> stringResource(id = R.string.error_coding)
            is PureError.Storage            -> stringResource(id = R.string.error_storage)
            is PureError.Unknown            -> stringResource(id = R.string.error_unknown)
        }
        Text(text = title)

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = onRetry,
               content = { Text(text = stringResource(id = R.string.button_try_again)) })

    }
}