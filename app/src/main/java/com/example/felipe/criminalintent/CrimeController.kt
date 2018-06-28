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
            this.add(Crime(title = "Crime $i", isPoliceRequire = (i % 10) == 5))
        }
    }

    public fun add(crime: Crime) = listOfCrime.add(crime)

    public fun remove(crime: Crime) = listOfCrime.remove(crime)

    public fun listCrimes() = listOfCrime.toList()

    public fun getCrime(id: UUID): Crime? = listOfCrime.first { it.id == id }
}
