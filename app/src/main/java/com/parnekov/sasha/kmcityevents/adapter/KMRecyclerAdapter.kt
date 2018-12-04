package com.parnekov.sasha.kmcityevents.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.annotation.GlideModule
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.activity.EventDetailsActivity
import com.parnekov.sasha.kmcityevents.firebase.*
import com.parnekov.sasha.kmcityevents.model.KMEvent

@GlideModule
class KMRecyclerAdapter(var context: Context, private var KMEventList: MutableList<KMEvent>) : RecyclerView.Adapter<KMHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KMHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_event, parent, false)
        return KMHolder(view)
    }

    override fun getItemCount(): Int {
        return KMEventList.size
    }

    override fun onBindViewHolder(kmHolder: KMHolder, position: Int) {
        val event: KMEvent = KMEventList[position]
        kmHolder.title.text = event.title
        kmHolder.price.text = event.price
        kmHolder.date.text = event.date
        kmHolder.address.text = event.location

        kmHolder.itemView.setOnClickListener {
            val intent = Intent(context, EventDetailsActivity::class.java)
            intent.putExtra(TITLE, event.title)
            intent.putExtra(PRICE, event.price)
            intent.putExtra(DATE, event.date)
            intent.putExtra(TIME, event.time)
            intent.putExtra(LOCATION, event.location)
            intent.putExtra(IMAGE_URL, event.image)
            intent.putExtra(PHONE, event.mobileNumber)
            intent.putExtra(DESCRIPTION, event.description)
            context.startActivity(intent)
        }
    }
}

class KMHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.text_view_title_event)
    var price: TextView = itemView.findViewById(R.id.text_view_price)
    var date: TextView = itemView.findViewById(R.id.text_view_date_time)
    var address: TextView = itemView.findViewById(R.id.text_view_address)
}