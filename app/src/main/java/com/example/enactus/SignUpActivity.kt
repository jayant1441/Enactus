package com.example.enactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

        btn_signUp.setOnClickListener {
            NoEmptyFields()

            auth.createUserWithEmailAndPassword(et_signup_email.text.toString(), et_signup_pass.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    updateUI(user)
                                }
                            }
                    }
                    else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                    }
                }

        }

        iv_back_signup.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        tv_already_have_an_acc.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }

    private fun updateUI(user:FirebaseUser?){
        startActivity(Intent(this,LoginActivity::class.java))
        Toast.makeText(baseContext, "Please verify your email",Toast.LENGTH_SHORT).show()
    }
    private fun NoEmptyFields(){

        if (et_signup_name.text.isEmpty()){
            et_signup_name.error = "Please enter your name"
            et_signup_name.requestFocus()
        }
        if (et_signup_number.text.isEmpty()){
            et_signup_number.error = "Please enter your number"
            et_signup_number.requestFocus()
        }
        if (et_signup_email.text.isEmpty()){
            et_signup_email.error = "Please enter your email"
            et_signup_email.requestFocus()
        }
        if (et_signup_pass.text.isEmpty()){
            et_signup_pass.error = "Please enter password"
            et_signup_pass.requestFocus()
        }
        if (et_signup_number.text.length<10 || et_signup_number.text.length>10){
            et_signup_number.error = "Please enter correct number"
            et_signup_number.requestFocus()
        }
        if (et_signup_pass.text.length < 8){
            et_signup_pass.error = "Please enter 8 character long Password"
            et_signup_pass.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_signup_email.text.toString()).matches()){
            et_signup_email.error = "Please enter correct email"
            et_signup_email.requestFocus()
        }
    }


}
