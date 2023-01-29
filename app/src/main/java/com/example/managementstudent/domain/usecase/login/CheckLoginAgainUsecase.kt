package com.example.managementstudent.domain.usecase.login

import com.example.managementstudent.domain.repository.LoginInterface
import javax.inject.Inject

class CheckLoginAgainUsecase @Inject constructor(val loginInterface: LoginInterface) {
    suspend operator fun invoke() = loginInterface.checkLoginAgain()
}