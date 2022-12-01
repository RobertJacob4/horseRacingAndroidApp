package ie.wit.horseRacingApp.models

import timber.log.Timber.i

class RaceMemStore : RaceStore {

    val races = ArrayList<RaceModel>()

    override fun findAll(): List<RaceModel> {
        return races
    }

    override fun create(race: RaceModel) {
        races.add(race)
        logAll()
    }

    fun logAll() {
        races.forEach{ i("${it}") }
    }
}