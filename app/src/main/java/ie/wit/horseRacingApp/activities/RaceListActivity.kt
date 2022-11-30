package ie.wit.horseRacingApp.activities


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem


import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.horseRacingApp.R

import ie.wit.horseRacingApp.databinding.ActivityRaceListBinding
import ie.wit.horseRacingApp.databinding.CardRaceBinding
import ie.wit.horseRacingApp.main.MainApp
import ie.wit.horseRacingApp.models.RaceModel

class RaceListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityRaceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRaceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = RaceAdapter(app.races)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, HorseRaceActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.races.size)
            }
        }
}


class RaceAdapter constructor(private var races: List<RaceModel>) :
    RecyclerView.Adapter<RaceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRaceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val race = races[holder.adapterPosition]
        holder.bind(race)
    }

    override fun getItemCount(): Int = races.size

    class MainHolder(private val binding : CardRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(race: RaceModel) {
            binding.raceTitle.text = race.title
            binding.description.text = race.description
        }
    }

}