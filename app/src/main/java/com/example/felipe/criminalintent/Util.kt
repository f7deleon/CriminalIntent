package com.example.felipe.criminalintent

import android.app.Activity
import android.content.Intent

fun Activity.finishActivityWithResult(intent: Intent) {
    this.setResult(Activity.RESULT_OK, intent)
    this.finish()
}
