package com.example.modul5.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.modul5.R
import androidx.navigation.fragment.findNavController
import com.example.modul5.databinding.FragmentHomeBinding
import com.example.modul5.ui.adapter.HomePagerAdapter
import com.example.modul5.viewmodel.TasViewModel
import com.example.modul5.viewmodel.TasViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private val viewModel: TasViewModel by activityViewModels {
        TasViewModelFactory(requireContext())
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HomePagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) "All Product" else "Favorite"
        }.attach()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigateToDetail.collect {
                val selected = viewModel.selectedTas.value
                if (selected != null) {
                    findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
                } else {
                    Log.e("HomeFragment", "navigateToDetail: selectedTas is null")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
