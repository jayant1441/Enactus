package com.example.enactus.LoginSignUp

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.room.Room
import com.example.enactus.MainActivity
import com.example.enactus.R
import com.example.enactus.RoomDatabase.RoomEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.time.LocalDate
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()

  //      val db = Room.databaseBuilder(applicationContext, RoomDB::class.java, "database_name").build()



//btn_signUp.setOnClickListener {
//    Thread{
//        var now = Calendar.getInstance()
//        val mypref  = getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val newest_water = mypref.getString("waterdrank","0")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            var current_date = LocalDate.now()
//            var room_entity = RoomEntity(3 ,3,newest_water!!)
//            db.RoomDao().insertAll(room_entity)
//            Log.d("done","mission successful")
//        } else {
//            var current_date =  Date()
//        }
//    }.start()
//
//}





        btn_signUp.setOnClickListener {
           NoEmptyFields()
           // if (!et_signup_email.text.isEmpty() && !et_signup_name.text.isEmpty() && !et_signup_number.text.isEmpty() && !et_signup_pass.text.isEmpty() && (et_signup_number.text.length<10 && et_signup_number.text.length>10) && (Patterns.EMAIL_ADDRESS.matcher(et_signup_email.text.toString()).matches())){
                auth.createUserWithEmailAndPassword(et_signup_email.text.toString(), et_signup_pass.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        user?.sendEmailVerification()
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    UploadToDatabase()
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
////            else{
////                Toast.makeText(baseContext, "Fill all fields",Toast.LENGTH_SHORT).show()
////
////            }
//        }

        iv_back_signup.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }

        tv_already_have_an_acc.setOnClickListener {
            startActivity(Intent(this,
                LoginActivity::class.java))
            this.finish()
        }


    }

    private fun updateUI(user:FirebaseUser?){
        startActivity(Intent(this,
            LoginActivity::class.java))
        this.finish()
        Toast.makeText(baseContext, "Please verify your email",Toast.LENGTH_SHORT).show()
    }
    private fun NoEmptyFields(){

        if (et_signup_name.text.isEmpty()){
            et_signup_name.error = "Please enter your name"
            et_signup_name.requestFocus()
            return
        }
        if (et_signup_number.text.isEmpty()){
            et_signup_number.error = "Please enter your number"
            et_signup_number.requestFocus()
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
        if (et_signup_number.text.length<10 && et_signup_number.text.length>10){
            et_signup_number.error = "Please enter correct number"
            et_signup_number.requestFocus()
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



    inner class DatabaseDataClass(val name: String, val phoneNumber:Int, val email:String, val password :String,val uuid:String)

    private fun UploadToDatabase(){
        var uuid = FirebaseAuth.getInstance().uid
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("/users/$uuid")
        val user  = DatabaseDataClass(et_signup_name.text.toString(), et_signup_number.text.toString().toInt(), et_signup_email.text.toString(), et_signup_pass.text.toString(), uuid!!)
        ref.setValue(user)
    }
}

