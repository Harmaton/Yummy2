package com.example.yummy2.screens

sealed  class Screen(val route : String) {
    object Welcome : Screen(route = "welcome_screen")
    object Home : Screen(route = "Home")
}