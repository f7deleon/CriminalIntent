package com.example.felipe.criminalintent

import java.util.*

class Crime {
    private var id : UUID
    var date: Date
    lateinit var title: String
    var isSolved = false

    init {
        id = UUID.randomUUID()
        date = Date()
    }
}