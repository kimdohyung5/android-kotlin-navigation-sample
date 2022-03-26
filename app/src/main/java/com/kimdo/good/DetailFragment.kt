package com.kimdo.good

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kimdo.good.databinding.FragmentDetailBinding
import com.kimdo.good.databinding.FragmentListBinding


class DetailFragment : Fragment() {

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

        binding.fabList.setOnClickListener{
            val action = DetailFragmentDirections.actionList()
            Navigation.findNavController(it).navigate( action )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}