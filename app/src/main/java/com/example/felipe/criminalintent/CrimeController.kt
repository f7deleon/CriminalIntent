package com.example.felipe.criminalintent

import java.util.*

class CrimeController {
    private val listOfCrime = mutableListOf<Crime>()

    init {
        for (i in 1..50) {
            this.add(Crime(title = "Crime $i", isPoliceRequire = (i % 10) == 5))
        }
    }

    class SingletonClass private constructor() {
        companion object {
            private val INSTANCE = CrimeController()

            fun getInstance() = INSTANCE
        }
    }

    public fun add(crime: Crime) {
        listOfCrime.add(crime)
    }

    public fun remove(crime: Crime) {
        listOfCrime.remove(crime)
    }

    public fun listCrimes() = listOfCrime.toList()

    public fun getCrime(id: UUID): Crime? {
        for (i in listOfCrime) {
            if (i.id == id) return i
        }
        return null
    }
}
