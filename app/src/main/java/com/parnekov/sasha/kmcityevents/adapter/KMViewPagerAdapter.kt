package com.parnekov.sasha.kmcityevents.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class KMViewPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm){

    var fragments: MutableList<Fragment> = mutableListOf()
    var tabTitles: MutableList<String> = mutableListOf()

    override fun getItem(position: Int): Fragment? {
        return fragments[position]
    }

    override fun getCount(): Int {
       return fragments.size
    }

    fun addFragments(fragments: MutableList<Fragment>, tabTitles: MutableList<String>){
        this.fragments.addAll(fragments)
        this.tabTitles.addAll(tabTitles)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}
