package com.example.yummy2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.yummy2.navigation.SetUpNavGraph
import com.example.yummy2.ui.theme.Yummy2Theme
import com.example.yummy2.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isloading.value
        }


        setContent {
            Yummy2Theme {
                val screen by splashViewModel.startDestination
             val navController = rememberNavController()
                SetUpNavGraph(navcontroller = navController, startDestination = screen)
                }
            }
        }
    }


