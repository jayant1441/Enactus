package com.example.enactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        btn_login.setOnClickListener {
            NoEmptyFields()

            try {
                auth.signInWithEmailAndPassword(et_login_email.text.toString(), et_login_password.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }

                }
            }
            catch (e:Exception){
                Toast.makeText(baseContext, "Exception", Toast.LENGTH_SHORT).show()
            }
        }


        iv_back_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        tv_login_to_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            this.finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null){
            updateUI(currentUser)
        }
    }

    private fun updateUI(user:FirebaseUser?){
        val currentUser = auth.currentUser
        if (currentUser!=null){
            if(currentUser.isEmailVerified){
                startActivity(Intent(this,MainActivity::class.java))
            }
        }
    }


    private fun NoEmptyFields(){

        if (et_login_email.text.isEmpty()){
            et_login_email.error = "Please enter your email"
            et_login_email.requestFocus()
        }
        if (et_login_password.text.isEmpty()){
            et_login_password.error = "Please enter password"
            et_login_password.requestFocus()
        }

    }
}
