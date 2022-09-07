package com.example.yummy2.screens

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.yummy2.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun AuthenticationScreen(navcontroller: NavHostController) {

    val auth = Firebase.auth
    val user = auth.currentUser

    if (user != null) {
        navcontroller.navigate(Screen.Home.route)
    }

    var email by remember {
        mutableStateOf("")
    }

    var passWord by remember {
        mutableStateOf("")
    }

    var isPassWordVisible by remember {
        mutableStateOf(false)
    }

    val isFormValid by derivedStateOf {
        email.isNotEmpty()
        passWord > 7.toString()
    }
    Scaffold(backgroundColor = Color.White) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.mipmap.userlogin),
                contentDescription = "user login png",
                modifier = Modifier
                    .weight(1f)
                    .size(128.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )

            Card(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {

                Text(
                    text = "Welcome Back Foodie",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    OutlinedTextField(modifier = Modifier.fillMaxSize(),
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "email") },
                        singleLine = true,
                        trailingIcon = {

                            if (email.isNotEmpty()) {
                                IconButton(onClick = { email = "" }) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "x Icon"
                                    )
                                }
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(modifier = Modifier.fillMaxSize(),
                        value = passWord,
                        onValueChange = { passWord = it },
                        label = { Text(text = "PassWord") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        visualTransformation = if (isPassWordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { isPassWordVisible = !isPassWordVisible }) {
                                Icon(
                                    imageVector = if (isPassWordVisible) Icons.Default.Clear else Icons.Default.Check,
                                    contentDescription = "password toggle"
                                )
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            auth.signInWithEmailAndPassword(email, passWord)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d(TAG, "Sign in with email successful")
                                        navcontroller.popBackStack()
                                        navcontroller.navigate(Screen.Home.route)
                                    } else {
                                        Log.d(TAG, "Log in Failed")
                                    }

                                }
                        },
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(18.dp),
                        enabled = isFormValid
                    ) {
                        Text(text = "Log In")
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "Sign Up")
                        }
                        TextButton(onClick = { /*TODO*/ }) {
                            Text(text = "Forgot Password?", color = Color.Gray)
                        }
                    }
                }
            }
        }
    }

}

