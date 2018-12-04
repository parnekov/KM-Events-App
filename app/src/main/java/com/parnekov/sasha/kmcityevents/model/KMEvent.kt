package com.parnekov.sasha.kmcityevents.model

data class KMEvent(
    val image: String?,
    val date: String,
    val time: String,
    val title: String,
    val location: String,
    val description: String?,
    val price: String,
    val mobileNumber: String?)