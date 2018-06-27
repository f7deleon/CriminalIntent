package com.example.felipe.criminalintent

import java.util.UUID
import java.util.Date

data class Crime(var date: Date = Date(), var title: String = "", var isSolved: Boolean = false) {
    var id: UUID = UUID.randomUUID()
}
