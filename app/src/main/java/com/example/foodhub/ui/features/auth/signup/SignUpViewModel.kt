package com.example.foodhub.ui.features.auth.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodhub.data.FoodApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(val foodApi : FoodApi):ViewModel() {

    private val _uiState = MutableStateFlow<SignUpEvent>(SignUpEvent.Nothing)
    val uiState = _uiState

    private val _navigationEvent = MutableSharedFlow<SignupNavigation>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _name = MutableStateFlow("")
    val name = _name

    fun onEmailChange(email:String){
        _email.value = email
    }

    fun onPasswordChange(password:String){
        _password.value = password
    }

    fun onNameChange(name:String){
        _name.value = name
    }

     fun onSignupClick() {
         viewModelScope.launch {
             _uiState.value = SignUpEvent.Loading
             delay(2000)
             _uiState.value = SignUpEvent.Success
             _navigationEvent.tryEmit(SignupNavigation.NavigateToHome)
         }

     }


    sealed class SignupNavigation{
        object NavigateToLogin:SignupNavigation()
        object NavigateToHome:SignupNavigation()
    }


    sealed class SignUpEvent{
        object Nothing:SignUpEvent()
        object Success:SignUpEvent()
        object Error:SignUpEvent()
        object Loading:SignUpEvent()
    }
}