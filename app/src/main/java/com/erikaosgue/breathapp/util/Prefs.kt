package com.erikaosgue.breathapp.util

import android.app.Activity
import android.content.SharedPreferences
import java.util.*

class Prefs(activity: Activity) {

    private var preferences: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    fun setDate(milliseconds: Long) {
        preferences.run { edit().putLong("seconds", milliseconds) }
            .apply()
    }


    val date: String get() {

        val milliDate = preferences.getLong("seconds", 0)
        val amORpm: String

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliDate

        val a = calendar.get(Calendar.AM_PM)
        amORpm = if (a == Calendar.AM) "AM" else "PM"

        return ("Last " +  calendar.get(Calendar.HOUR_OF_DAY) + ": " +
                calendar.get(Calendar.MINUTE) + " " + amORpm)

    }

    var sessions: Int
    get() = preferences.getInt("sessions", 0)
    set(session) = preferences.edit().putInt("sessions", session).apply()

    var breathes: Int
    get() = preferences.getInt("breathes", 0)
    set(breathe) = preferences.edit().putInt("breathes", breathe).apply()

}