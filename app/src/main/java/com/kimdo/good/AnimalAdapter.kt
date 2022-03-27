package com.kimdo.good

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.kimdo.good.databinding.ItemAnimalBinding
import com.kimdo.good.models.Animal
import com.kimdo.good.util.getProgressDrawable
import com.kimdo.good.util.loadImage

class AnimalAdapter(private val animalList:ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList:ArrayList<Animal>) {
        animalList.clear()
        animalList.addAll( newAnimalList )
        Log.d(TAG, "updateAnimalList: newAnimalList.size= ${newAnimalList.size}")
        notifyDataSetChanged()
    }

    class AnimalViewHolder( val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {

        val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AnimalViewHolder( binding )
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.binding.animalName.text = animalList[position].name
        holder.binding.animalImage.loadImage(animalList[position].image, getProgressDrawable(holder.itemView.context))

        holder.binding.animalLayout.setOnClickListener {
            val action = ListFragmentDirections.actionDetail(animalList[position])
            Navigation.findNavController(holder.itemView).navigate( action)
        }
    }

    override fun getItemCount(): Int {
        return animalList.size
    }

    companion object {
        val TAG = "AnimalAdapter"
    }
}
