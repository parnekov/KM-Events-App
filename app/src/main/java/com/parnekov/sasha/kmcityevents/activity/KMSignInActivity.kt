package com.parnekov.sasha.kmcityevents.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.firebase.SingInListener
import com.parnekov.sasha.kmcityevents.firebase.emailSignIn
import kotlinx.android.synthetic.main.activity_kmsign_in.*

class KMSignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmsign_in)

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            onNext()
        }
            button_sign_in.setOnClickListener {
                val email = edit_text_email.text.toString().trim()
                val pass  = edit_text_pass.text.toString().trim()
            emailSignIn(this, email, pass, object : SingInListener {
                    override fun onSuccess() {
                        Toast.makeText(this@KMSignInActivity, "Sign In Success", Toast.LENGTH_SHORT).show()
                        onNext()
                    }

                    override fun onFailed(exception: Exception) {
                        Log.w("KC_E", exception)
                        val message: String = when(exception){
                            is FirebaseAuthInvalidCredentialsException -> "Email address is badly formatted"
                            is FirebaseAuthInvalidUserException -> "Рas no such user, register please"
                            is FirebaseAuthWeakPasswordException -> "Рas no such user, register please"
                            is FirebaseNetworkException -> "Turn on The Internet."
                            else -> "Error, try again later"
                        }
                        text_view_error.text = message
                    }
                }
            )
        }

        text_view_sign_up.setOnClickListener {
            startActivity(Intent(this, KMSignUpActivity::class.java))
        }
    }

    fun onNext() {
        val intent = Intent(this@KMSignInActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
