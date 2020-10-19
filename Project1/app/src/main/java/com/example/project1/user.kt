package com.example.project1

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class user (
    @Id var userId: Long = 0,
    var username: String,
    var password: String
)