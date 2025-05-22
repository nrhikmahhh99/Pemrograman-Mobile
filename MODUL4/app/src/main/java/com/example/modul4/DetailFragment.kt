package com.example.modul4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.modul4.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {
    private lateinit var viewModel: TasViewModel
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
        viewModel = ViewModelProvider(requireActivity())[TasViewModel::class.java]
        lifecycleScope.launch {
            viewModel.selectedTas.collect { tas ->
                Log.d("DetailFragment", "Tas dari ViewModel: $tas")
                if (tas != null) {
                    binding.tvName.text = tas.nama
                    binding.tvHarga.text = getString(R.string.label_harga) + " " + tas.harga
                    binding.tvDeskripsi.text = tas.deskripsi
                    binding.imgItemPhoto.setImageResource(tas.gambar)
                } else {
                    Log.d("DetailFragment", "Tas masih null dari ViewModel")
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
