package com.example.yummy2.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.yummy2.viewmodels.WelcomeViewModel
import com.google.accompanist.pager.*

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navcontroller: NavHostController,
welcomeViewModel: WelcomeViewModel = hiltViewModel()
                  ) {
    val pages= listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third
    )
    val pagerState = rememberPagerState(pageCount = 3)

   Column(modifier = Modifier
       .fillMaxWidth()
       .background(Color.White),
   verticalArrangement = Arrangement.Top
       ) {
       HorizontalPager(state = pagerState , modifier = Modifier.weight(10f)) { position ->
           PagerScreen(onboardingPage = pages[position])
       }
       HorizontalPagerIndicator(
           activeColor = Color.Red,
           inactiveColor = Color.Black ,
           pagerState = pagerState,
           modifier = Modifier
               .align(Alignment.CenterHorizontally)
               .weight(1f)
       )
      FinishButton(modifier = Modifier.weight(1f), pagerstate = pagerState) {
          welcomeViewModel.SaveOnBoardingState(completed = true)
       navcontroller.popBackStack()
          navcontroller.navigate(Screen.Authentication.route)
      }
   }

}

@Composable
fun PagerScreen(onboardingPage : OnboardingPage){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        
        Image(painter = painterResource(id = onboardingPage.image),
            contentDescription = "Pager Image",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.5f)
        )
        
        Text(
            text = onboardingPage.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h3.fontSize,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = onboardingPage.description,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
                .padding(top = 20.dp)

        )

        
    }
}
@ExperimentalPagerApi
@Composable
fun FinishButton(
modifier: Modifier,
pagerstate:PagerState,
onClick : () -> Unit
){

    Row(
        modifier = modifier.padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AnimatedVisibility(visible = pagerstate.currentPage == 2,
        modifier = Modifier.fillMaxWidth()
            ) {
            OutlinedButton(onClick = onClick,
            colors = ButtonDefaults.buttonColors(contentColor = Color.Green),
                shape = RoundedCornerShape(12.dp)
                ) {
              Text(text = "Get started")
                Icon(imageVector = Icons.Default.ArrowForward,
                    contentDescription = "done button")
            }
        }
    }


}