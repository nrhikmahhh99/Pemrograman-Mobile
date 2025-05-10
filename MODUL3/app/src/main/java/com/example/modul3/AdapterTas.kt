package com.example.modul3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3.databinding.ItemTasBinding

class AdapterTas(
    private val listTas: List<ItemTas>,
    private val navController: NavController
) : RecyclerView.Adapter<AdapterTas.ListViewHolder>() {

    inner class ListViewHolder(val binding: ItemTasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tas: ItemTas) {
            binding.tvItemName.text = tas.nama
            binding.tvItemPrice.text = tas.harga
            binding.imgItemPhoto.setImageResource(tas.gambar)
            binding.buttonDetail.setOnClickListener {
                Log.d("AdapterTas", "Tombol detail diklik")
                val bundle = Bundle().apply {
                    putParcelable("tas", tas)
                }
                navController.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
            binding.buttonShopee.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tas.link))
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemTasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTas[position])
    }

    override fun getItemCount(): Int = listTas.size
}
