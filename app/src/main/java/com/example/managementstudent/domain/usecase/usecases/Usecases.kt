package com.example.managementstudent.domain.usecase.usecases

import com.example.managementstudent.domain.usecase.login.CheckLoginAgainUsecase
import com.example.managementstudent.domain.usecase.login.LoginUsecase

data class Usecases (
    val loginUsecase: LoginUsecase,
    val checkLoginAgainUsecase: CheckLoginAgainUsecase
    )