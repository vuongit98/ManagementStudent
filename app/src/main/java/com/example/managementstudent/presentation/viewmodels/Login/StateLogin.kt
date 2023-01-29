package com.example.managementstudent.presentation.viewmodels.Login

data class StateLogin(
    val isLoading : Boolean = true,
    var data : Boolean = false ,
    var error : String = ""
)
