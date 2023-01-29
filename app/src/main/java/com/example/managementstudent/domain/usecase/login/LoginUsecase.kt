package com.example.managementstudent.domain.usecase.login

import com.example.managementstudent.domain.repository.LoginInterface
import javax.inject.Inject

class LoginUsecase @Inject constructor(val loginInterface: LoginInterface) {
    suspend operator fun invoke(email: String, pass : String) = loginInterface.login(email, pass)
}