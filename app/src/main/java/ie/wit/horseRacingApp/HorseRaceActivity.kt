package ie.wit.horseRacingApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import ie.wit.horseRacingApp.databinding.ActivityHorseraceBinding
import timber.log.Timber
import timber.log.Timber.i


class HorseRaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHorseraceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horserace)

        Timber.plant(Timber.DebugTree())

        i("Horse Race Activity started..")

        binding = ActivityHorseraceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener() {
            val raceTitle = binding.raceTitle.text.toString()
            if (raceTitle.isNotEmpty()) {
                i("add Button Pressed: $raceTitle")
            }
            else {
                Snackbar
                    .make(it,"Please Enter a title", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}
