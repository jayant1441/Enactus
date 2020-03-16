package com.example.enactus.LoginSignUp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
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
import kotlinx.android.synthetic.main.fragment_fragment_settings.*


class LoginActivity : AppCompatActivity() {

    var RC_SIGN_IN = 123
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.title = "Login"

        btn_forgot_password_reset_link.visibility = View.GONE

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("SigningUp")
        progressDialog.setMessage("Please Wait it can take a moment")

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
           Log.d("hiworld" , personName!!)
        }

        auth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        var mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btn_login.setOnClickListener {
            Toast.makeText(baseContext, "Please wait", Toast.LENGTH_SHORT).show()
            NoEmptyFields()
            progressDialog.show()

            try {
                auth.signInWithEmailAndPassword(et_login_email.text.toString(), et_login_password.text.toString()).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                        progressDialog.dismiss()
                        Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_SHORT).show()

                    } else {
                        progressDialog.dismiss()
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



        tv_login_to_signup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            this.finish()
        }

        tv_forgot_password.setOnClickListener {

            et_login_password.visibility = View.GONE
//            btn_facebook.visibility = View.GONE
            btn_google.visibility = View.GONE
            btn_login.visibility = View.GONE
            tv_forgot_password.visibility = View.GONE
            btn_forgot_password_reset_link.visibility = View.VISIBLE
            tv_signin_to_ur_acc_text.text = "Enter your email"

        }

        btn_forgot_password_reset_link.setOnClickListener {

            if (et_login_email.text.isEmpty()){
                et_login_email.error = "Please enter your email"
                et_login_email.requestFocus()
                return@setOnClickListener
            }
            else {
                if (Patterns.EMAIL_ADDRESS.matcher(et_login_email.text.toString()).matches()){
                    val auth = FirebaseAuth.getInstance()
                    val emailAddress = et_login_email.text.toString()

                    auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this,"Password reset email sent" , Toast.LENGTH_SHORT ).show()
                            }
                            else{
                                Toast.makeText(this,"User doesn't exist", Toast.LENGTH_SHORT ).show()

                            }
                        }
                }
                else{
                    et_login_email.error = "Please enter correct email"
                    et_login_email.requestFocus()
                    return@setOnClickListener
                }

            }

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
            val google_account_name_pref = getSharedPreferences("google_account_name_pref" ,Context.MODE_PRIVATE)
            val google_account_name_pref_editor = google_account_name_pref.edit()
            google_account_name_pref_editor.putString("Login_key" , "Welcome\n${account!!.displayName}")
            google_account_name_pref_editor.apply()

            Toast.makeText(this,"Welcome : " + account!!.displayName , Toast.LENGTH_SHORT ).show()
        }
        catch (e: ApiException) {
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
        if (currentUser!=null) {
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
        else{
            Toast.makeText(baseContext, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
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

















