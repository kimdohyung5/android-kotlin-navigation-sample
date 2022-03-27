package com.kimdo.good


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.kimdo.good.databinding.FragmentListBinding
import com.kimdo.good.models.Animal

class ListFragment : Fragment() {

    private  val viewModel by  viewModels<ListViewModel>()
    private val listAdapter = AnimalAdapter( arrayListOf() )
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate( inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.animalList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }
        viewModel.animals.observe(viewLifecycleOwner) { list ->
            list?.let {
                binding.animalList.visibility = View.VISIBLE
                listAdapter.updateAnimalList(it as ArrayList<Animal>)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.loadingView.visibility = if(loading) View.VISIBLE else View.GONE
            if(loading) {
                binding.listError.visibility = View.GONE
                binding.animalList.visibility = View.GONE
            }
        }
        viewModel.loadError.observe(viewLifecycleOwner) { loaderror ->
            binding.listError.visibility = if(loaderror) View.VISIBLE else View.GONE
        }
        viewModel.refresh()
//        binding.fabDetail.setOnClickListener {
//            Log.d(TAG, "onViewCreated: fabDetail")
//            val action = ListFragmentDirections.actionDetail()
//            Navigation.findNavController(it).navigate(action)
//        }

        binding.refreshLayout.setOnRefreshListener {
            binding.animalList.visibility = View.GONE
            binding.listError.visibility = View.GONE
            binding.loadingView.visibility = View.VISIBLE

            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = "ListFragment "
    }
}