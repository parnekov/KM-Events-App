package com.parnekov.sasha.kmcityevents.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.activity.EventsListActivity
import com.parnekov.sasha.kmcityevents.firebase.*
import kotlinx.android.synthetic.main.list_entertainment_layout.*

class TomorrowEventsFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("KM", "onCreateView")
        return inflater.inflate(R.layout.fragment_tomorrow_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("KM", "onViewCreated")

        image_view_disco.setOnClickListener { discoClick() }
        text_view_disco.setOnClickListener { discoClick() }
        image_view_cinema.setOnClickListener { cinemaClick() }
        text_view_cinema.setOnClickListener { cinemaClick() }
        image_view_concerts.setOnClickListener { concertsClick() }
        text_view_concerts.setOnClickListener { concertsClick() }
        image_view_theater.setOnClickListener { theaterClick() }
        text_view_theater.setOnClickListener { theaterClick() }
    }

    fun discoClick(){
        Toast.makeText(context, "Disco", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.action = DISCO_BLOCK
        intent.putExtra(TOMORROW, TOMORROW)
        startActivity(intent)
    }

    fun cinemaClick(){
        Toast.makeText(context, "Cinema", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.action = CINEMA_BLOCK
        intent.putExtra(TOMORROW, TOMORROW)
        startActivity(intent)
    }

    fun concertsClick(){
        Toast.makeText(context, "Concerts", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.action = CONCERTS_BLOCK
        intent.putExtra(TOMORROW, TOMORROW)
        startActivity(intent)
    }

    fun theaterClick(){
        Toast.makeText(context, "Theater", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, EventsListActivity::class.java)
        intent.action = THEATER_BLOCK
        intent.putExtra(TOMORROW, TOMORROW)
        startActivity(intent)
    }
}