package com.parnekov.sasha.kmcityevents.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.parnekov.sasha.kmcityevents.adapter.KMRecyclerAdapter
import com.parnekov.sasha.kmcityevents.model.KMEvent
import java.text.SimpleDateFormat
import java.util.*

const val EVENTS_TABLE: String = "Events"
const val DISCO_BLOCK: String = "Disco"
const val CINEMA_BLOCK: String = "Cinema"
const val CONCERTS_BLOCK: String = "Concerts"
const val THEATER_BLOCK: String = "Theater"

const val ALL = "All"
const val TODAY: String = "today"
const val TOMORROW: String = "tomorrow"

const val IMAGE_URL: String = "image_url"
const val DATE: String = "date"
const val TIME: String = "time"
const val TITLE: String = "title"
const val LOCATION: String = "location"
const val DESCRIPTION: String = "description"
const val PRICE: String = "price"
const val PHONE: String = "phone"

const val DAY: Int = 86400000

fun writeEventToDb(block: String, event: KMEvent) {

    val database = FirebaseDatabase.getInstance()
        .getReference(EVENTS_TABLE)
        .child(block)
        .child(System.currentTimeMillis().toString())

    database.child(IMAGE_URL).setValue(event.image)
    database.child(DATE).setValue(event.date)
    database.child(TIME).setValue(event.time)
    database.child(TITLE).setValue(event.title)
    database.child(DESCRIPTION).setValue(event.description)
    database.child(LOCATION).setValue(event.location)
    database.child(PHONE).setValue(event.mobileNumber)
    database.child(PRICE).setValue(event.price)

}
@SuppressLint("SimpleDateFormat")
fun readAllEventsFromDb(context: Context, eventBlock: String, recyclerView: RecyclerView) {
    val listOfEvents = mutableListOf<KMEvent>()
    Log.d("KC_E", "readAllEventsFromDb")
    val format = SimpleDateFormat("dd.MM.yyyy")

    val currentDay = Date(System.currentTimeMillis())
    val currentDateString = format.format(currentDay)
    Log.d("KC_E", "currentDateString - $currentDateString")


    val listener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d("KC_E", dataSnapshot.key)

            for (element in dataSnapshot.children) {
                Log.d("KC_E", element.key)


                val imageUrl: String = element.child(IMAGE_URL).value!! as String
                val date: String = element.child(DATE).value!! as String
                val time: String = element.child(TIME).value!! as String
                val title = element.child(TITLE).value!! as String
                val description: String? = element.child(DESCRIPTION).value as String
                val location: String = element.child(LOCATION).value!! as String
                val price: String = element.child(PRICE).value!! as String
                val phone: String? = element.child(PHONE).value as String

                if (format.parse(date).time >= format.parse(currentDateString).time) {
                    val event = KMEvent(imageUrl, date, time, title, location, description, price, phone)
                    listOfEvents.add(event)
                    Log.d("KC_E", "format.parse(date).time > currentDateString")
                }


            }
            Log.d("KC_E", listOfEvents.size.toString())
            // refresh adapter
            recyclerView.adapter = KMRecyclerAdapter(context, listOfEvents)

        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.d("KC_E", "${databaseError.toException()}")
        }
    }
    FirebaseDatabase.getInstance().getReference(EVENTS_TABLE).child(eventBlock).addListenerForSingleValueEvent(listener)
}
@SuppressLint("SimpleDateFormat")
fun readTodayEventsFromDb(context: Context, eventBlock: String, recyclerView: RecyclerView) {
    val listOfEvents = mutableListOf<KMEvent>()
    val currentDate = Date(System.currentTimeMillis())

    val format = SimpleDateFormat("dd.MM.yyyy")

    Log.d("KC_E", "readAllEventsFromDb")

    val listener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d("KC_E", dataSnapshot.key)

            for (element in dataSnapshot.children) {
                Log.d("KC_E", element.key)

                val imageUrl: String = element.child(IMAGE_URL).value!! as String
                val date: String = element.child(DATE).value!! as String
                val time: String = element.child(TIME).value!! as String
                val title = element.child(TITLE).value!! as String
                val description: String? = element.child(DESCRIPTION).value as String
                val location: String = element.child(LOCATION).value!! as String
                val price: String = element.child(PRICE).value!! as String
                val phone: String? = element.child(PHONE).value as String

                if (date == format.format(currentDate)) {
                    val event = KMEvent(imageUrl, date, time, title, location, description, price, phone)
                    listOfEvents.add(event)
                }
            }
            Log.d("KC_E", listOfEvents.size.toString())
            // refresh adapter
            recyclerView.adapter = KMRecyclerAdapter(context, listOfEvents)

        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.d("KC_E", "${databaseError.toException()}")
        }
    }
    FirebaseDatabase.getInstance().getReference(EVENTS_TABLE).child(eventBlock).addListenerForSingleValueEvent(listener)
}

@SuppressLint("SimpleDateFormat")
fun readTomorrowEventsFromDb(context: Context, eventBlock: String, recyclerView: RecyclerView) {
    val listOfEvents = mutableListOf<KMEvent>()
    val currentDate = Date(System.currentTimeMillis())
    val tomorrowDate = Date(currentDate.time + DAY)
    val format = SimpleDateFormat("dd.MM.yyyy")


    Log.d("KC_E", "readAllEventsFromDb")

    val listener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            Log.d("KC_E", dataSnapshot.key)

            for (element in dataSnapshot.children) {
                Log.d("KC_E", element.key)

                val imageUrl: String = element.child(IMAGE_URL).value!! as String
                val date: String = element.child(DATE).value!! as String
                val time: String = element.child(TIME).value!! as String
                val title = element.child(TITLE).value!! as String
                val description: String? = element.child(DESCRIPTION).value as String
                val location: String = element.child(LOCATION).value!! as String
                val price: String = element.child(PRICE).value!! as String
                val phone: String? = element.child(PHONE).value as String

                if (date == format.format(tomorrowDate)) {
                    val event = KMEvent(imageUrl, date, time, title, location, description, price, phone)
                    listOfEvents.add(event)
                }
            }
            Log.d("KC_E", listOfEvents.size.toString())
            // refresh adapter
            recyclerView.adapter = KMRecyclerAdapter(context, listOfEvents)
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.d("KC_E", "${databaseError.toException()}")
        }
    }
    FirebaseDatabase.getInstance().getReference(EVENTS_TABLE).child(eventBlock).addListenerForSingleValueEvent(listener)
}