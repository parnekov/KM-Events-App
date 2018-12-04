package com.parnekov.sasha.kmcityevents.datetime

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import java.util.*

class KMTimePicker: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val hour = Calendar.HOUR_OF_DAY
        val minute = Calendar.MINUTE

        return TimePickerDialog(context!!, activity as TimePickerDialog.OnTimeSetListener, hour, minute, true)
    }
}