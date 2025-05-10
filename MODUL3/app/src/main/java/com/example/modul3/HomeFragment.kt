package com.example.modul3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val listTas = generateTasList()
        val adapter = AdapterTas(listTas, findNavController())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateTasList(): List<ItemTas> {
        val context = requireContext()
        val names = context.resources.getStringArray(R.array.data_name)
        val harga = context.resources.getStringArray(R.array.data_harga)
        val deskripsi = context.resources.getStringArray(R.array.data_deskripsi)
        val links = context.resources.getStringArray(R.array.data_link)

        val imageIds = listOf(
            R.drawable.marsya_bag,
            R.drawable.lyn_bag,
            R.drawable.reigny_bag,
            R.drawable.madeline_bag,
            R.drawable.tote_bag,
            R.drawable.mini_cendy_bag,
            R.drawable.amora_bag,
            R.drawable.gradien_bag,
            R.drawable.lysmare_bag,
            R.drawable.delphine_bag
        )

        val list = mutableListOf<ItemTas>()
        for (i in names.indices) {
            list.add(
                ItemTas(
                    nama = names[i],
                    harga = harga[i],
                    deskripsi = deskripsi[i],
                    gambar = imageIds[i],
                    link = links[i]
                )
            )
        }
        return list
    }
}
