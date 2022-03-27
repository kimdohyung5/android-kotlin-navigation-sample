package com.kimdo.good

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kimdo.good.databinding.FragmentDetailBinding
import com.kimdo.good.models.Animal
import com.kimdo.good.util.getProgressDrawable
import com.kimdo.good.util.loadImage

class DetailFragment : Fragment() {

    var animal: Animal? = null
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }
        context?.let {
            binding.animalImage.loadImage(animal?.image, getProgressDrawable(it))
        }
        animal?.let {
            binding.animalName.text = it.name
            binding.animalDiet.text = it.diet
            binding.animalLifeSpan.text = it.lifespan
            binding.animalLocation.text = it.location
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}