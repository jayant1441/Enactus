package com.example.enactus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.enactus.Fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{item->
        when(item.itemId) {
            R.id.nav_home -> {
                replace_fragment(FragmentHome())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_period -> {
                replace_fragment(FragmentPeriod())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_water -> {
                replace_fragment(FragmentWater())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_meditation -> {
                replace_fragment(FragmentMeditation())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_settings -> {
                replace_fragment(FragmentSettings())
                return@OnNavigationItemSelectedListener true
            }
        }

        false



    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replace_fragment(FragmentHome())
    }

    fun replace_fragment(fragmentName:Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout,fragmentName)
        fragmentTransaction.commit()
    }


}
