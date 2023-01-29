package com.example.managementstudent.presentation.viewmodels.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managementstudent.Utils.Resource
import com.example.managementstudent.domain.usecase.usecases.Usecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(val usercase : Usecases) : ViewModel() {
    val stateLogin = MutableStateFlow(StateLogin(true,false,""))

    val _stateLogin : StateFlow<StateLogin> = stateLogin.asStateFlow()

    fun loginAccount(email : String , pass : String) {
        viewModelScope.launch {
            val result = usercase.loginUsecase(email, pass)
            result.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        stateLogin.value = StateLogin(isLoading = true, data = false)
                    }
                    is Resource.Success -> {
                        stateLogin.value = StateLogin(isLoading = false, data = it.data!!)
                    }
                    is Resource.Error -> {
                        stateLogin.value = StateLogin(isLoading = false, error = "${it.message.toString()}")
                    }
                }
            }
        }
    }
    fun checkAgain() {
        viewModelScope.launch {
            val result = usercase.checkLoginAgainUsecase()
            result.collectLatest {
                when(it) {
                    is Resource.Loading -> {
                        stateLogin.value = StateLogin(isLoading = true, data = false)
                    }
                    is Resource.Success -> {
                        stateLogin.value = StateLogin(isLoading = false, data = it.data!!)
                    }
                    is Resource.Error -> {
                        stateLogin.value = StateLogin(isLoading = false, data = false,error = it.message!!.toString())
                    }
                }
            }
        }
    }
}