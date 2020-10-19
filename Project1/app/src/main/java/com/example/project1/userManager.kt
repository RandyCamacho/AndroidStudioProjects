package com.example.project1

object userManager {
    private val userBox = ObjectBox.boxStore.boxFor(user::class.java)
}