package com.example.felipe.criminalintent

import java.util.UUID

class CrimeController private constructor() {
    companion object {
        private val INSTANCE = CrimeController()

        fun getInstance() = INSTANCE
    }

    private val listOfCrime = mutableListOf<Crime>()

    init {
        for (i in 1..50) {
            this.add(Crime(title = "Crime $i",
                    isSolved = (i % 10) == 5,
                    isPoliceRequire = (i % 9) == 0))
        }
    }

    fun add(crime: Crime) = listOfCrime.add(crime)

    fun remove(crime: Crime) = listOfCrime.remove(crime)

    fun listCrimes() = listOfCrime.toList()

    fun getCrime(id: UUID): Crime? = listOfCrime.first { it.id == id }

    fun getIndex(id: UUID?) = listOfCrime.indexOfFirst { it.id == id}
}
