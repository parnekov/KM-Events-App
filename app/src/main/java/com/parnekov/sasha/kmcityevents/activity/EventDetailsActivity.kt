package com.parnekov.sasha.kmcityevents.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.datetime.getDayFromDate
import com.parnekov.sasha.kmcityevents.datetime.getMonthFromDate
import com.parnekov.sasha.kmcityevents.firebase.*
import kotlinx.android.synthetic.main.activity_event_details.*

class EventDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_details)
        val intent = intent

        val image = intent?.extras?.getString(IMAGE_URL)
        val date = intent?.extras?.getString(DATE)
        val time = intent?.extras?.getString(TIME)
        val title = intent?.extras?.getString(TITLE)
        val location = intent?.extras?.getString(LOCATION)
        val description = intent?.extras?.getString(DESCRIPTION)
        val price = intent?.extras?.getString(PRICE)
        val phone = intent?.extras?.getString(PHONE)

        Glide.with(this)
            .load(image)
            .into(image_view_details)

        text_view_day.text = getDayFromDate(date!!)
        text_view_month.text = getMonthFromDate(date)
        text_view_time.text = time
        text_view_title.text = title
        text_view_club_name.text = location
        text_view_short_description.text = description
        text_view_price.text = price

        button_fab_call.setOnClickListener { callIntent(this, phone!!) }
    }
}
