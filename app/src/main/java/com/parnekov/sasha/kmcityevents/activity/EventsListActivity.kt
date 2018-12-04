package com.parnekov.sasha.kmcityevents.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.firebase.*
import kotlinx.android.synthetic.main.activity_events_list.*


class EventsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events_list)

        recycler_view_events_list.layoutManager = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_events_list)
        when (intent.action) {
            DISCO_BLOCK -> {
                when {
                    intent.getStringExtra(TODAY) == TODAY -> {
                        readTodayEventsFromDb(this, DISCO_BLOCK, recyclerView)
                    }
                    intent.getStringExtra(TOMORROW) == TOMORROW -> {
                        readTomorrowEventsFromDb(this, DISCO_BLOCK, recyclerView)
                    }
                    else -> readAllEventsFromDb(this, DISCO_BLOCK, recyclerView)
                }
            }

            CINEMA_BLOCK -> {
                when {
                    intent.getStringExtra(TODAY) == TODAY -> {
                        readTodayEventsFromDb(this, CINEMA_BLOCK, recyclerView)
                    }
                    intent.getStringExtra(TOMORROW) == TOMORROW -> {
                        readTomorrowEventsFromDb(this, CINEMA_BLOCK, recyclerView)
                    }
                    else -> readAllEventsFromDb(this, CINEMA_BLOCK, recyclerView)
                }
            }

            CONCERTS_BLOCK -> {
                when {
                    intent.getStringExtra(TODAY) == TODAY -> {
                        readTodayEventsFromDb(this, CONCERTS_BLOCK, recyclerView)
                    }
                    intent.getStringExtra(TOMORROW) == TOMORROW -> {
                        readTomorrowEventsFromDb(this, CONCERTS_BLOCK, recyclerView)
                    }
                    else -> readAllEventsFromDb(this, CONCERTS_BLOCK, recyclerView)
                }
            }

            THEATER_BLOCK -> {
                when {
                    intent.getStringExtra(TODAY) == TODAY -> {
                        readTodayEventsFromDb(this, THEATER_BLOCK, recyclerView)
                    }
                    intent.getStringExtra(TOMORROW) == TOMORROW -> {
                        readTomorrowEventsFromDb(this, THEATER_BLOCK, recyclerView)
                    }
                    else -> readAllEventsFromDb(this, THEATER_BLOCK, recyclerView)
                }
            }
            else -> Log.d("KC_E", "Something wrong")
        }
    }
}
