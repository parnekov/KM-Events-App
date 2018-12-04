package com.parnekov.sasha.kmcityevents.firebase

import android.app.Activity
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

interface SingUpListener {
    fun onSuccess()
    fun onFailed(exception: Exception)
}

interface SingInListener {
    fun onSuccess()
    fun onFailed(exception: Exception)
}

fun emailSignUp(activity: Activity, email: String, pass: String, listener: SingUpListener) {
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("KM", "signUpWithEmail:success")
                listener.onSuccess()
            } else {
                // If sign in fails, display a message to the user.
                Log.w("KM", "signUpWithEmail:failure", task.exception)
                listener.onFailed(Exception(task.exception))
            }

        }
}

fun emailSignIn(activity: Activity, email: String, pass: String, listener: SingInListener) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
        .addOnCompleteListener(activity) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("KM", "signInWithEmail:success")
                listener.onSuccess()
            } else {
                // If sign in fails, display a message to the user.
                Log.w("KM", "signInWithEmail:failure", task.exception)
                listener.onFailed(Exception(task.exception))
            }

        }
}