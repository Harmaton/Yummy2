package com.example.yummy2.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yummy2.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor( private  val repository: DataStoreRepository)  : ViewModel(){
    fun SaveOnBoardingState(completed : Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.SaveOnboardingState(completed = true)
        }
    }
}