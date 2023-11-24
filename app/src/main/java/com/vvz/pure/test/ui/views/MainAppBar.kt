package com.vvz.pure.test.ui.views

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.vvz.pure.test.ui.navigation.SystemBarsState
import com.vvz.pure.test.ui.navigation.rememberSystemBarState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainAppBar(state: SystemBarsState = rememberSystemBarState()) {

    CenterAlignedTopAppBar(title = { Text(text = state.title) },
                           navigationIcon = {
                               if (state.showBackButton) {
                                   IconButton(onClick = { state.onBack?.invoke() }) {
                                       Icon(imageVector = Icons.Filled.ArrowBack,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer)
                                   }
                               }
                           },
                           colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer,
                                                                                   titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                                                                   navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer))
}