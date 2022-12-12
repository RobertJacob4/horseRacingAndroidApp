package ie.wit.horseRacingApp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ie.wit.horseRacingApp.R
import ie.wit.horseRacingApp.databinding.ActivityHorseraceBinding
import ie.wit.horseRacingApp.helpers.showImagePicker
import ie.wit.horseRacingApp.main.MainApp
import ie.wit.horseRacingApp.models.Location

import ie.wit.horseRacingApp.models.RaceModel
import timber.log.Timber.i


class HorseRaceActivity : AppCompatActivity() {
    private lateinit var mapIntentLauncher : ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityHorseraceBinding
    var race = RaceModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    val IMAGE_REQUEST = 1
    //var location = Location(52.245696, -7.139102, 15f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var edit = false

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
            Picasso.get()
                .load(race.image)
                .into(binding.raceImage)
            if (race.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_race_image)
            }
        }
        binding.raceLocation.setOnClickListener {
            i ("Set Location Pressed")
        }

        binding.raceLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (race.zoom != 0f) {
                location.lat =  race.lat
                location.lng = race.lng
                location.zoom = race.zoom
            }
            val launcherIntent = Intent(this, MapActivity::class.java)
                .putExtra("location", location)
            mapIntentLauncher.launch(launcherIntent)
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
            showImagePicker(imageIntentLauncher,this)
        }
        registerImagePickerCallback()
        registerMapCallback()
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

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            race.image = image

                            Picasso.get()
                                .load(race.image)
                                .into(binding.raceImage)
                            binding.chooseImage.setText(R.string.change_race_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

    private fun registerMapCallback() {
        mapIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Location ${result.data.toString()}")
                            val location = result.data!!.extras?.getParcelable<Location>("location")!!
                            i("Location == $location")
                            race.lat = location.lat
                            race.lng = location.lng
                            race.zoom = location.zoom
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}
