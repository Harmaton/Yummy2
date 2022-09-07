package com.example.yummy2.screens

import androidx.annotation.DrawableRes
import com.example.yummy2.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
)
{
    object First : OnboardingPage(
        image = R.mipmap.food1,
        title = "Choose from over 1000+ Meals ",
        description = "Be in control of your diet at all time - your health is a priority "
    )
    object Second : OnboardingPage(
        image = R.mipmap.ingredients,
        title = "Know what you eat",
        description = "Ingredients from all over the world in your hand"
    )
    object Third : OnboardingPage(
        image = R.drawable.morefood_background,
        title = "Discover Easy To Make Meals",
        description = "Don't limit yourself, be exposed to all the secret recipes all over the world "
    )
}
