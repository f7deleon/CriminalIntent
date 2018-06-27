package com.example.felipe.criminalintent

import java.util.UUID
import java.util.Date

data class Crime(var date: Date = Date(), var title: String? = null, var isSolved: Boolean = false) {
    val id: UUID = UUID.randomUUID()
}
