package com.example.managementstudent.data.model

data class User(
    var id : String = "",
    var name : String = "",
    var imgPerson : String = "",
    var email : String ="",
    var address : String ="",
    var phone : String ="",
    var role  : Int = 0
) : java.io.Serializable
