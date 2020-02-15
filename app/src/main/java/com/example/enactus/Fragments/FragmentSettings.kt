package com.example.enactus.Fragments


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.enactus.*
import com.example.enactus.IntroSliderTextViewUpdate.IS_TV_Update2
import com.example.enactus.LoginSignUp.LoginActivity
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
        val tv_log_out = myfragment_settings.tv_log_out as TextView
        val tv_contact_us = myfragment_settings.tv_contact_us as TextView
        val tv_about_pcod = myfragment_settings.tv_about_pcod as TextView
        val tv_visit_website = myfragment_settings.tv_visit_website as TextView
        val tv_project_sana = myfragment_settings.tv_project_sana as TextView
        val tv_privacy_policy = myfragment_settings.tv_privacy_policy as TextView




        tv_login.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    LoginActivity::class.java
                )
            )
        }

        tv_contact_us.setOnClickListener {
            var intent =  Intent(Intent.ACTION_VIEW)
                .setType("plain/text")
                .setData(Uri.parse("mailto:enactushivaji@gmail.com"))
                .putExtra(Intent.EXTRA_SUBJECT, "Feedback")
            startActivity(intent)
        }



        tv_visit_website.setOnClickListener {
            Toast.makeText(context,"Opening Website" , Toast.LENGTH_SHORT).show()
            val intent_to_website = Intent(Intent.ACTION_VIEW, Uri.parse("http://enactusshivaji.org/"))
            startActivity(intent_to_website)
        }


        tv_project_sana.setOnClickListener {
            var intent_to_about_activity = Intent(context,AboutActivity::class.java)
            intent_to_about_activity.putExtra("title","About SANA")
            startActivity(intent_to_about_activity)
        }


        tv_about_pcod.setOnClickListener {
            var intent_to_about_page = Intent(context,AboutEnactus::class.java)
            intent_to_about_page.putExtra("about_pcod" , "About PCOD")
            intent_to_about_page.putExtra("about_des", "Polycystic ovarian disease or syndrome (PCOD ) is a syndrome that has shaken modern world by storm . It is a common condition that affects a woman’s hormone levels. It is a must that young girls understand this disease at an early stage . Basically , PCOD is a problem that affects any women during their childbearing years (age 15 to 44). A large no. of women have PCOD but don’t know it. In one study, up to 70 percent of women with PCOD hadn’t been diagnosed . PCOD generally affects the ovaries and ovulation. The most common PCOD symptoms are:\n" +
                    "\t Irregular periods. \n" +
                    "\t Heavy bleeding. \n" +
                    "\t Hair growth. \n" +
                    "\tn• Acne. \n" +
                    "\t Weight gain. \n" +
                    "\t Male-pattern baldness. \n" +
                    "\t Darkening of the skin like on the neck, in the groin, and under the breasts.\n" +
                    "\t Headaches.\\n\\n\n" +
                    "It can also affect a woman’s health in many ways : infertility, metabolic syndrome, endometrial cancer, depression along with risk for pregnancy complications and miscarriage")
            startActivity(intent_to_about_page)
        }


        tv_privacy_policy.setOnClickListener {
            var intent_to_privacy_policy = Intent(context,PrivacyPolicy::class.java)
            intent_to_privacy_policy.putExtra("privacy_policy_title" , "Privacy Policy")
            intent_to_privacy_policy.putExtra("privacy_policy_des" , "The app just store your name and Email address and doesn't send anywhere.\n\nBy signing in from google also we store your name and email address.\n\nAll your data is safe.")
            startActivity(intent_to_privacy_policy)


        }

        return myfragment_settings
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val google_account_name_pref = context!!.getSharedPreferences("google_account_name_pref" , Context.MODE_PRIVATE)
        tv_login.text = google_account_name_pref.getString("Login_key","Login")

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

        tv_developer.setOnClickListener {
            startActivity(Intent(context,Developer::class.java))
        }

        tv_change_settings_all.setOnClickListener {
            startActivity(Intent(context, IS_TV_Update2::class.java))
        }



    }
}
