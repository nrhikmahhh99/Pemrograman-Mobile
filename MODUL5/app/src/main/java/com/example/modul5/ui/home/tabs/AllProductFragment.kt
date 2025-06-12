package com.example.modul5.ui.home.tabs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul5.databinding.FragmentAllProductBinding
import com.example.modul5.ui.adapter.AdapterTas
import com.example.modul5.viewmodel.TasViewModel
import com.example.modul5.viewmodel.TasViewModelFactory
import kotlinx.coroutines.launch

class AllProductFragment : Fragment() {
    private var _binding: FragmentAllProductBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TasViewModel by activityViewModels {
        TasViewModelFactory(requireContext()) }
    private lateinit var adapter: AdapterTas

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAllProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = AdapterTas(
            onDetailClick = { viewModel.selectTas(it) },
            onShopeeClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.shopeeLink))
                startActivity(intent)
            },
            onFavoriteClick = { viewModel.toggleFavorite(it) }
        )
        binding.recyclerViewAll.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@AllProductFragment.adapter
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasList.collect { list ->
                adapter.updateList(list)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
