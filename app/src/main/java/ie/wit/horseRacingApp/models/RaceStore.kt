package ie.wit.horseRacingApp.models

interface RaceStore {
    fun findAll(): List<RaceModel>
    fun create(race: RaceModel)
}