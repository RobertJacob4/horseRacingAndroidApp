package ie.wit.horseRacingApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.horseRacingApp.databinding.CardRaceBinding
import ie.wit.horseRacingApp.models.RaceModel

interface RaceListener {
    fun onRaceClick(race: RaceModel, position : Int)
}

class RaceAdapter constructor(private var races: List<RaceModel>,
                                   private val listener: RaceListener) :
    RecyclerView.Adapter<RaceAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardRaceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val race = races[holder.adapterPosition]
        holder.bind(race, listener)
    }

    override fun getItemCount(): Int = races.size

    class MainHolder(private val binding : CardRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(race: RaceModel, listener: RaceListener) {
            binding.raceTitle.text = race.title
            binding.description.text = race.description
            Picasso.get().load(race.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onRaceClick(race,adapterPosition) }
        }
    }
}