package ie.wit.horseRacingApp.main

import android.app.Application
import ie.wit.horseRacingApp.models.RaceJSONStore
import ie.wit.horseRacingApp.models.RaceMemStore
import ie.wit.horseRacingApp.models.RaceStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var races: RaceStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        races = RaceJSONStore(applicationContext)
        i("Race started")
    }
}