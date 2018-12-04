package com.parnekov.sasha.kmcityevents.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.DialogFragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import com.google.firebase.auth.FirebaseAuth
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.datetime.KMDatePicker
import com.parnekov.sasha.kmcityevents.datetime.KMTimePicker
import com.parnekov.sasha.kmcityevents.firebase.uploadBitmapToStorage
import kotlinx.android.synthetic.main.activity_add_event.*
import java.text.SimpleDateFormat
import java.util.*

const val REQUEST_CODE: Int = 382
var date: String? = null
var time: String? = null

class AddEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        button_date.setOnClickListener {
            val datePicker: DialogFragment = KMDatePicker()
            datePicker.show(supportFragmentManager, "datePicker")
        }
        button_time.setOnClickListener {
            val timePicker: DialogFragment = KMTimePicker()
            timePicker.show(supportFragmentManager, "timePicker")
        }
        button_add_event.setOnClickListener {
            showFileChooserTest()
        }

        button_log_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            finish()
            startActivity(Intent(this@AddEventActivity, SplashActivity::class.java))
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c  = Calendar.getInstance()
        c.set(year, month, dayOfMonth)
        val format = SimpleDateFormat("dd.MM.yyyy")
        date = format.format(c.time)
        button_date.text = date
    }

    @SuppressLint("SimpleDateFormat")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val c  = Calendar.getInstance()
        c.set(hourOfDay, minute)
        val format = SimpleDateFormat("HH:mm")
        time = format.format(c.time)
        button_time.text = time
    }

    private fun showFileChooserTest() {
        Log.d("KC_E", "showFileChooserTest: ")
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select an file"), 382)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("KC_E", "onActivityResult: $requestCode $resultCode $data")
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val uriFilePath = data.data
            Log.d("KC_E", "onActivityResult: " + "uriFilePath - " + uriFilePath!!.toString())

            val title = edit_title.text.toString()
            val date = button_date.text.toString()
            val time = button_time.text.toString()
            val location = edit_location.text.toString()
            val price = edit_price.text.toString()
            val description = edit_description.text.toString()
            val mobileNumber = edit_phone.text.toString()
            val block = spin_blocks.selectedItem.toString()

            val imgUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imgUri)

            uploadBitmapToStorage(this@AddEventActivity, block, uriFilePath, title, date, time, location,
                price, mobileNumber, description, bitmap)
            finish()
            startActivity(Intent(this@AddEventActivity, MainActivity::class.java))
        }
    }
}

