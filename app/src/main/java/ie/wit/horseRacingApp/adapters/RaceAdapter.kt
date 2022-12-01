package ie.wit.horseRacingApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ie.wit.horseRacingApp.databinding.CardRaceBinding
import ie.wit.horseRacingApp.models.RaceModel

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