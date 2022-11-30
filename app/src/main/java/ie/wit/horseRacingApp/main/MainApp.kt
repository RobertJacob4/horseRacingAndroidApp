package ie.wit.horseRacingApp.main

import android.app.Application
import ie.wit.horseRacingApp.models.RaceModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val races = ArrayList<RaceModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Race started")
        races.add(RaceModel("one", "About one..."))
        races.add(RaceModel("two", "About two..."))
        races.add(RaceModel("three", "About three..."))
    }
}