package ie.wit.horseRacingApp.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RaceMemStore : RaceStore {

    val races = ArrayList<RaceModel>()

    override fun findAll(): List<RaceModel> {
        return races
    }

    override fun create(race: RaceModel) {
        race.id = getId()
        races.add(race)
        logAll()
    }

    override fun update(race: RaceModel) {
        val foundRace: RaceModel? = races.find { p -> p.id == race.id }
        if (foundRace != null) {
            foundRace.title = race.title
            foundRace.description = race.description
            foundRace.type = race.type
            foundRace.size = race.size
            foundRace.image = race.image
            foundRace.lat = race.lat
            foundRace.lng = race.lng
            foundRace.zoom = race.zoom
            logAll()
        }
    }
    override fun delete(race: RaceModel) {
        races.remove(race)
    }
    private fun logAll() {
        races.forEach { i("$it") }
    }
}