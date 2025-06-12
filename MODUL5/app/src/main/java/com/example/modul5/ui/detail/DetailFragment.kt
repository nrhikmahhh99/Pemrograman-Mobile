package com.example.modul5.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.modul5.R
import com.example.modul5.databinding.FragmentDetailBinding
import com.example.modul5.viewmodel.TasViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.example.modul5.viewmodel.TasViewModelFactory

class DetailFragment : Fragment() {
    private val viewModel: TasViewModel by activityViewModels {
        TasViewModelFactory(requireContext())
    }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbar)
        activity.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "ShopBag – Your Bag Your Style"
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launch {
            viewModel.selectedTas.collectLatest { tas ->
                if (tas != null) {
                    binding.tvName.text = tas.nama
                    binding.tvHarga.text = getString(R.string.label_harga) + ": " + tas.harga
                    binding.tvDeskripsi.text = tas.deskripsi
                    binding.imgItemPhoto.load(tas.gambar) {
                        placeholder(R.drawable.placeholder)
                        error(R.drawable.ic_error)
                    }
                } else {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearSelectedTas()
        _binding = null
    }
}
