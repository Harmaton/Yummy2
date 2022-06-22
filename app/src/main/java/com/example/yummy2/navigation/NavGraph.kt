package com.example.yummy2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yummy2.screens.HomeScreen
import com.example.yummy2.screens.Screen
import com.example.yummy2.screens.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SetUpNavGraph(
    navcontroller: NavHostController
){
   NavHost(navController = navcontroller, startDestination = Screen.Welcome.route){
       composable(route=Screen.Welcome.route){ WelcomeScreen(navcontroller = navcontroller) }
       composable(route=Screen.Home.route){ HomeScreen() }
   }
}
