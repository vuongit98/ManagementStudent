package com.example.managementstudent.data.repository

import com.example.managementstudent.Utils.Resource
import com.example.managementstudent.domain.repository.LoginInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(val firebaseAuth : FirebaseAuth) : LoginInterface {
    override suspend fun login(email: String, pass: String): Flow<Resource<Boolean>> = callbackFlow{
        trySend(Resource.Loading())

        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener {
                trySend(Resource.Success(data = true))
        }.addOnFailureListener {
            trySend(Resource.Error(false, message = it.message.toString()))
        }
        awaitClose {
        }
    }

    override suspend fun checkLoginAgain(): Flow<Resource<Boolean>> = flow {
        val mAuth = firebaseAuth.currentUser
        emit(Resource.Loading())
        if (mAuth != null) {
            emit(Resource.Success(data = true))
        }else {
            emit(Resource.Error(data = false, message = "Account is not firebase"))
        }
    }
}