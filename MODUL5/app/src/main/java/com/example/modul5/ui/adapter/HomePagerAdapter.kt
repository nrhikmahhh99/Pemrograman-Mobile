package com.example.modul5.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.modul5.ui.home.tabs.AllProductFragment
import com.example.modul5.ui.home.tabs.FavoriteFragment

class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllProductFragment()
            1 -> FavoriteFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
