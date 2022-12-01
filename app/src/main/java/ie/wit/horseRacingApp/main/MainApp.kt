package ie.wit.horseRacingApp.main

import android.app.Application
import ie.wit.horseRacingApp.models.RaceMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val races = RaceMemStore()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Race started")
    }
}