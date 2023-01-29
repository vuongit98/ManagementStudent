package com.example.managementstudent.Utils

sealed class Resource<T> (val data : T? = null ,val message : String?= null) {
     class Loading<T>(data: T? =null, message: String?="") : Resource<T>(data, message)
     class Success<T>(data: T? =null, message: String?="") : Resource<T>(data,message)
     class Error <T>(data: T?= null, message: String?="") : Resource<T>(data,message)
}