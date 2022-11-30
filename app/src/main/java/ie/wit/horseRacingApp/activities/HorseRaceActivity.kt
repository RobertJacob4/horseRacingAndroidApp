package ie.wit.horseRacingApp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.wit.horseRacingApp.databinding.ActivityHorseraceBinding
import ie.wit.horseRacingApp.models.RaceModel
import timber.log.Timber
import timber.log.Timber.i


class HorseRaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorseraceBinding
    var race = RaceModel()
    val races = ArrayList<RaceModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorseraceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Timber.plant(Timber.DebugTree())
        i("Race Activity started...")

        binding.btnAdd.setOnClickListener() {
            race.title = binding.raceTitle.text.toString()
            race.description = binding.description.text.toString()
            if (race.title.isNotEmpty()) {
                races.add(race.copy())
                i("add Button Pressed: ${race}")
                for (i in races.indices)
                { i("Race[$i]:${this.races[i]}") }
            }
            else {
                Snackbar.make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
