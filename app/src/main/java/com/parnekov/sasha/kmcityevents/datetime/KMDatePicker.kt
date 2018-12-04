package com.parnekov.sasha.kmcityevents.datetime

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.*

class KMDatePicker: DialogFragment(){
 @SuppressLint("SimpleDateFormat")
 override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
     val currentDay = System.currentTimeMillis()
     val formatYear = SimpleDateFormat("yyyy")
     val formatMonth = SimpleDateFormat("MM")
     val formatDay = SimpleDateFormat("dd")

     val year: Int = formatYear.format(currentDay).toInt()
     val month: Int = formatMonth.format(currentDay).toInt()
     val day: Int = formatDay.format(currentDay).toInt()

  return DatePickerDialog(context!!, activity as DatePickerDialog.OnDateSetListener, year, month, day)
 }
}

fun getDayFromDate(dateFromDB: String):String{
    val dateD: Date = SimpleDateFormat("dd.MM.yyyy").parse(dateFromDB)
    val day = SimpleDateFormat("dd")
    return day.format(dateD)
}

fun getMonthFromDate(dateFromDB: String):String{
    val dateD: Date = SimpleDateFormat("dd.MM.yyyy").parse(dateFromDB)
    val month = SimpleDateFormat("MMM, yyyy")
    return month.format(dateD)
}