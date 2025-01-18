package com.example.foodhub.ui.features.auth.login

import androidx.lifecycle.viewModelScope
import com.example.foodhub.data.FoodApi
import com.example.foodhub.data.models.SignInRequest
import com.example.foodhub.data.remote.ApiResponse
import com.example.foodhub.data.remote.safeApiCall
import com.example.foodhub.ui.features.auth.BaseAuthViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignInViewModel @Inject constructor(override val foodApi : FoodApi): BaseAuthViewModel(foodApi) {



    private val _uiState = MutableStateFlow<SignInEvent>(SignInEvent.Nothing)
    val uiState = _uiState

    private val _navigationEvent = MutableSharedFlow<SigInNavigationEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password


    fun onEmailChange(email:String){
        _email.value = email
    }

    fun onPasswordChange(password:String){
        _password.value = password
    }
    fun onSignInClick() {
        viewModelScope.launch {
            _uiState.value = SignInEvent.Loading


                val response = safeApiCall {
                    foodApi.signIn(
                        SignInRequest(
                            email = email.value,
                            password = password.value
                        )

                    )
                }
                when(response){
                    is ApiResponse.Success -> {
                            _uiState.value = SignInEvent.Success
                            _navigationEvent.emit(SigInNavigationEvent.NavigateToHome)

                    }
                    else -> {
                        val errr = (response as? ApiResponse.Error)?.code?:0
                        error = "Sign In Failed"
                        errorDescription="Failed to sign Up"
                        when(errr){
                            401 -> {
                                errorDescription = "Invalid Credentials"
                                errorDescription = "Please check your email and password"
                            }
                            500 -> errorDescription = "Server Error"
                        }
                        _uiState.value = SignInEvent.Error

                    }
                }





        }

    }



    fun onSignUpClicked() {
        viewModelScope.launch {
            _navigationEvent.emit(SigInNavigationEvent.NavigateToSignUp)
        }
    }


    sealed class SigInNavigationEvent{
        object NavigateToSignUp:SigInNavigationEvent()
        object NavigateToHome:SigInNavigationEvent()
    }


    sealed class SignInEvent{
        object Nothing:SignInEvent()
        object Success:SignInEvent()
        object Error:SignInEvent()
        object Loading:SignInEvent()
    }

    override fun loading() {
        _uiState.value = SignInEvent.Loading
    }

    override fun onGoogleError(msg: String) {
        viewModelScope.launch {
            errorDescription =msg
            error="Google Sign In Failed"
            _uiState.value = SignInEvent.Error

        }
    }

    override fun onFacebookError(msg: String) {
       viewModelScope.launch {
           errorDescription =msg
           error="Facebook Sign In Failed"
           _uiState.value = SignInEvent.Error
       }
    }

    override fun onSocialLoginSuccess(token: String) {
        viewModelScope.launch {
            _uiState.value = SignInEvent.Success
            _navigationEvent.emit(SigInNavigationEvent.NavigateToHome)
        }
    }
}












