package com.parnekov.sasha.kmcityevents.firebase

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.parnekov.sasha.kmcityevents.model.KMEvent
import java.io.ByteArrayOutputStream

fun uploadBitmapToStorage(context: Context,
                          storageFolder: String,
                          uriImagePath:Uri,
                          title: String,
                          date: String,
                          time: String,
                          location: String,
                          price: String,
                          mobileNumber: String,
                          description: String,
                          bitmap: Bitmap) {

    val fileName: String = uriImagePath.lastPathSegment!!

    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos)
    val data = baos.toByteArray()

    val storageReference: StorageReference = FirebaseStorage.getInstance().reference
        .child(storageFolder)
        .child(fileName)

    val task = Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
        storageReference.downloadUrl
    }


    val completeListener = OnCompleteListener<Uri> { task ->
        val url: String = task.result.toString()

        writeEventToDb(storageFolder, KMEvent(url, date, time, title, location, description, price, mobileNumber))
        Log.d("KC_E", url)
        Toast.makeText(context, "Event Added", Toast.LENGTH_SHORT).show()
    }

    storageReference.putBytes(data).continueWithTask(task).addOnCompleteListener(completeListener)
}