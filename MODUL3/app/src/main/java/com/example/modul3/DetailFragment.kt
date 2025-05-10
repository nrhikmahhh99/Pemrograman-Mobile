package com.example.modul3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.modul3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

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

        Log.d("DetailFragment", "Arguments: $arguments")
        val tas = arguments?.getParcelable<ItemTas>("tas")
        Log.d("DetailFragment", "Tas received: $tas")
        tas?.let {
            binding.tvName.text = it.nama
            binding.tvHarga.text = getString(R.string.label_harga) + " " + it.harga
            binding.tvDeskripsi.text = it.deskripsi
            binding.imgItemPhoto.setImageResource(it.gambar)
        }
    }

    private fun getImageResourceIdByName(name: String): Int {
        val cleanName = name.replace("@drawable/", "")
        return resources.getIdentifier(cleanName, "drawable", requireContext().packageName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
