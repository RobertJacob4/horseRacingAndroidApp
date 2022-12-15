package ie.wit.horseRacingApp.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import ie.wit.horseRacingApp.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "races.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<RaceModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RaceJSONStore(private val context: Context) : RaceStore {

    var races = mutableListOf<RaceModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RaceModel> {
        logAll()
        return races
    }

    override fun create(race: RaceModel) {
        race.id = generateRandomId()
        races.add(race)
        serialize()
    }


    override fun update(race: RaceModel) {
        val racesList = findAll() as ArrayList<RaceModel>
        var foundRace: RaceModel? = racesList.find { p -> p.id == race.id }
        if (foundRace != null) {
            foundRace.title = race.title
            foundRace.description = race.description
            foundRace.type = race.type
            foundRace.size = race.size
            foundRace.image = race.image
            foundRace.lat = race.lat
            foundRace.lng = race.lng
            foundRace.zoom = race.zoom
        }
        serialize()
    }

    override fun delete(race: RaceModel) {
        races.remove(race)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(races, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        races = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        races.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}