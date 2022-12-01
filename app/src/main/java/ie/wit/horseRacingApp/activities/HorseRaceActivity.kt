package ie.wit.horseRacingApp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import ie.wit.horseRacingApp.R
import ie.wit.horseRacingApp.databinding.ActivityHorseraceBinding
import ie.wit.horseRacingApp.main.MainApp

import ie.wit.horseRacingApp.models.RaceModel
import timber.log.Timber.i


class HorseRaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHorseraceBinding
    var race = RaceModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        var edit = false

        super.onCreate(savedInstanceState)
        binding = ActivityHorseraceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarAdd.title = title
        setSupportActionBar(binding.toolbarAdd)
        app = application as MainApp

        if (intent.hasExtra("race_edit")) {
            edit = true
            race = intent.extras?.getParcelable("race_edit")!!
            binding.raceTitle.setText(race.title)
            binding.description.setText(race.description)
            binding.btnAdd.setText(R.string.save_race)
        }

        binding.btnAdd.setOnClickListener() {
            race.title = binding.raceTitle.text.toString()
            race.description = binding.description.text.toString()
            if (race.title.isEmpty()) {
                Snackbar.make(it,R.string.enter_race_title, Snackbar.LENGTH_LONG)
                    .show()
            } else {
                if (edit) {
                    app.races.update(race.copy())
                } else {
                    app.races.create(race.copy())
                }
            }
            setResult(RESULT_OK)
            finish()
        }
        binding.chooseImage.setOnClickListener {
            i("Select image")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_race, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> { finish() }
        }
        return super.onOptionsItemSelected(item)
    }
}
