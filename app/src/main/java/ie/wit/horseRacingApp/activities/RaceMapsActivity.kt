package ie.wit.horseRacingApp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap

import ie.wit.horseRacingApp.databinding.ActivityRaceMapsBinding
import ie.wit.horseRacingApp.databinding.ContentRaceMapsBinding

class RaceMapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaceMapsBinding
    private lateinit var contentBinding: ContentRaceMapsBinding
    lateinit var map: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRaceMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        contentBinding = ContentRaceMapsBinding.bind(binding.root)
        contentBinding.mapView.onCreate(savedInstanceState)

    }


    override fun onDestroy() {
        super.onDestroy()
        contentBinding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        contentBinding.mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        contentBinding.mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        contentBinding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        contentBinding.mapView.onSaveInstanceState(outState)
    }
}