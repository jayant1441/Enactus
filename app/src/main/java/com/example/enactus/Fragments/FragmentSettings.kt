package com.example.enactus.Fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.enactus.AboutActivity
import com.example.enactus.AboutEnactus
import com.example.enactus.LoginSignUp.LoginActivity
import com.example.enactus.MainActivity
import com.example.enactus.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_fragment_settings.*
import kotlinx.android.synthetic.main.fragment_fragment_settings.view.*


class FragmentSettings : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myfragment_settings =
            inflater.inflate(R.layout.fragment_fragment_settings, container, false)

        val tv_login = myfragment_settings.tv_login as TextView
        val tv_delete_Account = myfragment_settings.tv_delete_Account as TextView
        val tv_log_out = myfragment_settings.tv_log_out as TextView
        val tv_contact_us = myfragment_settings.tv_contact_us as TextView
        val tv_about_us = myfragment_settings.tv_about_us as TextView
        val tv_visit_website = myfragment_settings.tv_visit_website as TextView
        val tv_project_sana = myfragment_settings.tv_project_sana as TextView


        tv_login.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    LoginActivity::class.java
                )
            )
        }

        tv_about_us.setOnClickListener {
            var intent =  Intent(Intent.ACTION_VIEW)
                .setType("plain/text")
                .setData(Uri.parse("enactushivaji@gmail.com"))
                .setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail")
                .putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(intent);
        }





        tv_visit_website.setOnClickListener {
            val intent_to_website =
                Intent(Intent.ACTION_VIEW, Uri.parse("http://enactusshivaji.org/"))
            startActivity(intent_to_website)
        }

        tv_project_sana.setOnClickListener {
            var intent_to_about_activity = Intent(context,AboutActivity::class.java)
            intent_to_about_activity.putExtra("title","About SANA")
            startActivity(intent_to_about_activity)
        }

        tv_about_us.setOnClickListener {
            startActivity(Intent(context, AboutEnactus::class.java))
        }

        return myfragment_settings
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (FirebaseAuth.getInstance().currentUser != null) {
            tv_login.text = "Welcome"
            tv_login.isEnabled = false
        }

        tv_log_out.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser != null) {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(context, "user Logged out", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, LoginActivity::class.java))
                MainActivity().finish()
                tv_login.text = "Login"
                tv_login.isEnabled = true

            } else {
                Toast.makeText(context, "Login first", Toast.LENGTH_SHORT).show()

            }

        }


    }
}
