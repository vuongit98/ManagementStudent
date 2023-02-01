package com.example.managementstudent.data.model

import java.io.Serializable

data class Grade (
        val idGrade : String = "" ,
        val nameGrade : String ="",
        val numberTotalStudent : Int = 20,
        val year : String ="",
        ) : Serializable