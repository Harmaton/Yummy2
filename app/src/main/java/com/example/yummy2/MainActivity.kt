package com.example.yummy2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.yummy2.navigation.SetUpNavGraph
import com.example.yummy2.ui.theme.Yummy2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Yummy2Theme {
             val navController = rememberNavController()
                SetUpNavGraph(navcontroller = navController)
                }
            }
        }
    }


