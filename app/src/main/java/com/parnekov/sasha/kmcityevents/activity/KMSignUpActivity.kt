package com.parnekov.sasha.kmcityevents.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.parnekov.sasha.kmcityevents.R
import com.parnekov.sasha.kmcityevents.firebase.SingUpListener
import com.parnekov.sasha.kmcityevents.firebase.emailSignUp
import kotlinx.android.synthetic.main.activity_kmsign_up.*

class KMSignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kmsign_up)

        button_sign_up.setOnClickListener {
            emailSignUp(
                this, edit_text_email.text.toString().trim(),
                edit_text_pass.text.toString().trim(),
                object : SingUpListener {
                    override fun onSuccess() {
                        Toast.makeText(this@KMSignUpActivity, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        onNext()
                    }

                    override fun onFailed(exception: Exception) {
                        Log.w("KC_E", exception)
                        text_view_error.text = exception.message
                    }
                }
            )
        }
    }

    fun onNext() {
        val intent = Intent(this@KMSignUpActivity, MainActivity::class.java)
        startActivity(intent)
    }
}
