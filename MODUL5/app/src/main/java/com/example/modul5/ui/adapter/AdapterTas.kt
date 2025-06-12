package com.example.modul5.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.modul5.R
import com.example.modul5.databinding.ItemTasBinding
import com.example.modul5.domain.model.Tas

class AdapterTas(
    private val onDetailClick: (Tas) -> Unit,
    private val onShopeeClick: (Tas) -> Unit,
    private val onFavoriteClick: (Tas) -> Unit
) : RecyclerView.Adapter<AdapterTas.TasViewHolder>() {
    private var tasList: List<Tas> = emptyList()

    fun updateList(newList: List<Tas>) {
        tasList = newList
        notifyDataSetChanged()
    }
    inner class TasViewHolder(val binding: ItemTasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasViewHolder {
        val binding = ItemTasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasViewHolder, position: Int) {
        val tas = tasList[position]
        with(holder.binding) {
            tvItemName.text = tas.nama
            tvItemPrice.text = tas.harga
            imgItemPhoto.load(tas.gambar)
            btnFavorite.setImageResource(
                if (tas.isFavorite) R.drawable.ic_love_filled else R.drawable.ic_love_border
            )
            btnFavorite.setOnClickListener { onFavoriteClick(tas) }
            buttonShopee.setOnClickListener { onShopeeClick(tas) }
            buttonDetail.setOnClickListener { onDetailClick(tas) }
        }
    }

    override fun getItemCount(): Int = tasList.size
}
