package com.example.enactus.LoginSignUp


import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import com.example.enactus.IntroSliderTextViewUpdate.IntroSliderTV_update_main
import com.example.enactus.MainActivity
import com.example.enactus.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar!!.title = "SignUp"

        auth = FirebaseAuth.getInstance()


        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("SigningUp")
        progressDialog.setMessage("Please Wait it can take a moment")




        btn_signUp.setOnClickListener {
            NoEmptyFields()
            progressDialog.show()
                  auth.createUserWithEmailAndPassword(et_signup_email.text.toString(), et_signup_pass.text.toString()).addOnCompleteListener(this) { task ->
                      if (task.isSuccessful) {
                          // Sign in success, update UI with the signed-in user's information
                          val user = auth.currentUser
                          UploadToDatabase()
                          progressDialog.dismiss()
                          updateUI(user)
                      }
                      else {
                          progressDialog.dismiss()
                          Toast.makeText(baseContext, "Authentication failed.",Toast.LENGTH_SHORT).show()
                      }
                  }
            }



        tv_already_have_an_acc.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
            this.finish()
        }


    }

    private fun updateUI(user:FirebaseUser?){
        startActivity(Intent(this, IntroSliderTV_update_main::class.java))
        this.finish()
    }
    private fun NoEmptyFields(){

        if (et_signup_name.text.isEmpty()){
            et_signup_name.error = "Please enter your name"
            et_signup_name.requestFocus()
            return
        }
        if (et_signup_email.text.isEmpty()){
            et_signup_email.error = "Please enter your email"
            et_signup_email.requestFocus()
            return
        }
        if (et_signup_pass.text.isEmpty()){
            et_signup_pass.error = "Please enter password"
            et_signup_pass.requestFocus()
            return
        }
        if (et_signup_pass.text.length < 8){
            et_signup_pass.error = "Please enter 8 character long Password"
            et_signup_pass.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(et_signup_email.text.toString()).matches()){
            et_signup_email.error = "Please enter correct email"
            et_signup_email.requestFocus()
            return
        }
    }



    class DatabaseDataClass(val name: String,  val email:String, val password :String,val uuid:String){
        constructor():this("","","","")
    }
    private fun UploadToDatabase(){
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("/users")

        val current_user_uid = FirebaseAuth.getInstance().currentUser!!.uid

        val user  = DatabaseDataClass(et_signup_name.text.toString(), et_signup_email.text.toString(), et_signup_pass.text.toString(), current_user_uid)
        ref.child(current_user_uid).setValue(user).addOnCompleteListener {
            Toast.makeText(this,"Your account is created successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shared_pref(name: String){
        val google_account_name_pref = getSharedPreferences("google_account_name_pref" , Context.MODE_PRIVATE)
        val google_account_name_pref_editor = google_account_name_pref.edit()
        google_account_name_pref_editor.putString("Login_key" , "Greetings\n${name}")
        google_account_name_pref_editor.apply()

    }
}

