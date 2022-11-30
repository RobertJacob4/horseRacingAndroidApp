package ie.wit.horseRacingApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber
import timber.log.Timber.i


class HorseRaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horserace)

        Timber.plant(Timber.DebugTree())

        i("Horse Race Activity started..")
    }
}
