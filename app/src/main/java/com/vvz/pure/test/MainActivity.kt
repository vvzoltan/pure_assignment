package com.vvz.pure.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vvz.pure.test.di.dagger.ProvideViewModelFactory
import com.vvz.pure.test.ui.navigation.BottomNavigationGraph
import com.vvz.pure.test.ui.theme.PureTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PureApplication.appComponent.inject(this)

        setContent {
            PureTestTheme {
                ProvideViewModelFactory(viewModelFactory = PureApplication.appComponent.getViewModelFactory()) {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        BottomNavigationGraph()
                    }
                }
            }
        }
    }
}