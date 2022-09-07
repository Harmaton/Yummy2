package com.example.yummy2.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummy2.datastore.DataStoreRepository
import com.example.yummy2.screens.Screen
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(dataStoreRepository: DataStoreRepository): ViewModel() {
    private val _isloading : MutableState<Boolean> = mutableStateOf(true)
    val isloading: State<Boolean> = _isloading

    private val _startdestination: MutableState<String> = mutableStateOf(Screen.Welcome.route)
    val startDestination: State<String> = _startdestination

    init {
        viewModelScope.launch {
            dataStoreRepository.readOnboardingState().collect { completed ->
                if(completed){
                    _startdestination.value=Screen.Home.route
                } else{
                    _startdestination.value=Screen.Welcome.route
                }
            }
            _isloading.value= false
        }
    }
}