package com.example.felipe.criminalintent

import java.util.UUID

class CrimeController private constructor() {
    companion object {
        const val BEGINING = 1
        const val END = 50
        const val IS_SOLVED_MOD = 10
        const val IS_SOLVED_EQUAL = 5
        const val IS_POLICE_MOD = 9
        const val IS_POLICE_EQUAL = 0
        private val INSTANCE = CrimeController()

        fun getInstance() = INSTANCE
    }

    private val listOfCrime = mutableListOf<Crime>()

    val size = listOfCrime.size

    fun add(crime: Crime) = listOfCrime.add(crime)

    fun remove(crime: Crime) = listOfCrime.remove(crime)

    fun listCrimes() = listOfCrime.toList()

    fun getCrime(id: UUID): Crime? = listOfCrime.first { it.id == id }
}
