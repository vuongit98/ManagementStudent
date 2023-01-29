package com.example.managementstudent.domain.repository

import com.example.managementstudent.Utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginInterface {
    suspend fun login(email : String , pass : String) :  Flow<Resource<Boolean>>
    suspend fun checkLoginAgain() : Flow<Resource<Boolean>>
}