package com.example.enactus.LoginSignUp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.enactus.MainActivity
import com.example.enactus.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    var RC_SIGN_IN = 123
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
           Log.i("hi" , personName!!)
        }

        auth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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

        btn_google.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            startActivity(Intent(this,MainActivity::class.java))


        } catch (e: ApiException) {

            updateUI(null)
            Toast.makeText(this,"Error: " + e , Toast.LENGTH_SHORT ).show()
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
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
            else{
                Toast.makeText(baseContext, "Verify your email", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun NoEmptyFields(){

        if (et_login_email.text.isEmpty()){
            et_login_email.error = "Please enter your email"
            et_login_email.requestFocus()
            return
        }
        if (et_login_password.text.isEmpty()){
            et_login_password.error = "Please enter password"
            et_login_password.requestFocus()
            return
        }

    }
}

















