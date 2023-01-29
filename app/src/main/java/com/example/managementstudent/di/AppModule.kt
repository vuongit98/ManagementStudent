package com.example.managementstudent.di

import com.example.managementstudent.Utils.Constants
import com.example.managementstudent.data.repository.LoginRepositoryImpl
import com.example.managementstudent.domain.repository.LoginInterface
import com.example.managementstudent.domain.usecase.login.CheckLoginAgainUsecase
import com.example.managementstudent.domain.usecase.login.LoginUsecase
import com.example.managementstudent.domain.usecase.usecases.Usecases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebase() : DatabaseReference {
        return FirebaseDatabase.getInstance(Constants.FIREBASE_URL).reference
    }
    @Provides
    @Singleton
    fun probidesFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesLoginRepository( firebaseDatabase: FirebaseAuth) : LoginInterface {
        return LoginRepositoryImpl(firebaseDatabase)
    }

    @Provides
    @Singleton
    fun providesUsecase(loginInterface: LoginInterface) :Usecases{
        return Usecases(
            loginUsecase = LoginUsecase(loginInterface),
            checkLoginAgainUsecase = CheckLoginAgainUsecase(loginInterface)
        )
    }
}
