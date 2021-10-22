package com.example.enactus.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.enactus.PeriodFragmentFunction.Adapters.RV_AdapterFluidDischarge
import com.example.enactus.PeriodFragmentFunction.Adapters.RV_AdapterPain
import com.example.enactus.PeriodFragmentFunction.Adapters.RV_AdapterPhysicalParams
import com.example.enactus.PeriodFragmentFunction.Adapters.RV_SexDriveAdapter
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_FluidDataClass
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_PainDataClass
import com.example.enactus.PeriodFragmentFunction.DataClass.RV_SexDataClass
import com.example.enactus.PeriodFragmentFunction.DataClass.RecyclerViewDataClass
import com.example.enactus.R
import kotlinx.android.synthetic.main.fragment_period.*
import kotlinx.android.synthetic.main.fragment_period.view.*


class FragmentPeriod : Fragment() {

    var listOfItems = ArrayList<RecyclerViewDataClass>()
    var listofPainItems = ArrayList<RV_PainDataClass>()
    var listOfFluidItems = ArrayList<RV_FluidDataClass>()
    var listOfSexDriveItems = ArrayList<RV_SexDataClass>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val IS_sleep_time_pref =
            context!!.getSharedPreferences("IS_sleep_time_pref", Context.MODE_PRIVATE)
        val IS_weight_pref = context!!.getSharedPreferences("IS_weight_pref", Context.MODE_PRIVATE)
        val IS_height_pref = context!!.getSharedPreferences("IS_height_pref", Context.MODE_PRIVATE)


        val myfragment_period = inflater.inflate(R.layout.fragment_period, container, false)
        LoadPainRV()
        LoadFluidRV()
        LoadSexDriveRV()

        listOfItems.add(
            RecyclerViewDataClass(
                R.drawable.weight,
                "Weight",
                R.color.purple,
                IS_weight_pref.getString("IS_Weight", "60") + " Kg"
            )
        )
        listOfItems.add(
            RecyclerViewDataClass(
                R.drawable.height,
                "Height",
                R.color.purple,
                IS_height_pref.getString("IS_Height", "155") + " Cm"
            )
        )
        listOfItems.add(
            RecyclerViewDataClass(
                R.drawable.sleep,
                "Sleep",
                R.color.purple,
                IS_sleep_time_pref.getString("Sleep time", "No Time\nSelected")
            )
        )


        val rv = myfragment_period.recycler_view as RecyclerView
        val rv_pain_and_symptoms = myfragment_period.rv_pain_and_symptoms as RecyclerView
        val rv_fluid_vaginal_discharge =
            myfragment_period.rv_fluid_vaginal_discharge as RecyclerView
        //     val rv_Pill = myfragment_period.rv_Pill as RecyclerView
//        val rv_bleeding = myfragment_period.rv_bleeding as RecyclerView
//        val rv_Sleep = myfragment_period.rv_Sleep as RecyclerView
//        val rv_Energy = myfragment_period.rv_Energy as RecyclerView
//        val rv_Moods = myfragment_period.rv_Moods as RecyclerView
        val rv_Sex = myfragment_period.rv_Sex as RecyclerView
//        val rv_State_of_mind = myfragment_period.rv_State_of_mind as RecyclerView

        val et_anything_to_remember = myfragment_period.et_anything_to_remember








        rv_pain_and_symptoms.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_pain_and_symptoms.adapter = RV_AdapterPain(context!!, listofPainItems)

        rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = RV_AdapterPhysicalParams(context!!, listOfItems)

        rv_fluid_vaginal_discharge.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_fluid_vaginal_discharge.adapter = RV_AdapterFluidDischarge(context!!, listOfFluidItems)

//        rv_Pill.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_Pill.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )

//        rv_bleeding.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_bleeding.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )

//        rv_Sleep.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_Sleep.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )

//        rv_Energy.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_Energy.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )
//
//        rv_Moods.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_Moods.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )
//
        rv_Sex.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_Sex.adapter = RV_SexDriveAdapter(context!!, listOfSexDriveItems)
//
//        rv_State_of_mind.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//        rv_State_of_mind.adapter =
//            RV_AdapterPhysicalParams(
//                context!!,
//                listOfItems
//            )

        return myfragment_period
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        retrieveData_anything_to_remember()

        btn_update_period_frag.setOnClickListener {
            saveData_anything_to_remember()
        }

        btn_cancel.setOnClickListener {
            et_anything_to_remember.setText("")
        }
    }

//    private fun loadRecyclerViewList() {
//
//        val IS_sleep_time_pref = context!!.getSharedPreferences("IS_sleep_time_pref" , Context.MODE_PRIVATE)
//
//
//        listOfItems.add(RecyclerViewDataClass(R.drawable.weight, "Weight", R.color.purple, "hi"))
//        listOfItems.add(RecyclerViewDataClass(R.drawable.height, "Height", R.color.purple,"hi"))
//        listOfItems.add(RecyclerViewDataClass(R.drawable.sleep, "Sleep", R.color.purple, IS_sleep_time_pref.getString("Sleep time", "Select Time")))
//    }

    private fun LoadPainRV() {
        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.like,
                "Everything's\nFine",
                R.color.colorAccent

            )
        )
        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.fat,
                "Cramps",
                R.color.colorAccent
            )
        )
        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.breast,
                "Tender\nBreast",
                R.color.colorAccent
            )
        )

        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.pain,
                "Headache",
                R.color.colorAccent
            )
        )

        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.acne,
                "Acne",
                R.color.colorAccent
            )
        )

        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.battery,
                "Fatigue",
                R.color.colorAccent
            )
        )

        listofPainItems.add(
            RV_PainDataClass(
                R.drawable.swelled,
                "Swelling",
                R.color.colorAccent
            )
        )
    }

    private fun LoadFluidRV() {
        listOfFluidItems.add(
            RV_FluidDataClass(
                R.drawable.close,
                "No \n Discharge",
                R.color.yellow
            )
        )
        listOfFluidItems.add(
            RV_FluidDataClass(
                R.drawable.splash,
                "Watery",
                R.color.yellow
            )
        )
        listOfFluidItems.add(
            RV_FluidDataClass(
                R.drawable.stain,
                "Spotting",
                R.color.yellow

            )
        )

        listOfFluidItems.add(
            RV_FluidDataClass(
                R.drawable.protein,
                "Egg White",
                R.color.yellow
            )
        )
        listOfFluidItems.add(
            RV_FluidDataClass(
                R.drawable.unusual,
                "Unusual",
                R.color.yellow
            )
        )
    }

    private fun LoadSexDriveRV() {
        listOfSexDriveItems.add(
            RV_SexDataClass(
                R.drawable.heart_break,
                "No sex",
                R.color.blue
            )
        )

        listOfSexDriveItems.add(
            RV_SexDataClass(
                R.drawable.shield,
                "Protected\nSex",
                R.color.blue
            )
        )

        listOfSexDriveItems.add(
            RV_SexDataClass(
                R.drawable.heartbeat,
                "Unprotected \n Sex",
                R.color.blue
            )
        )


        listOfSexDriveItems.add(
            RV_SexDataClass(
                R.drawable.heart,
                "Mastur-\n" +
                        "bation",
                R.color.blue
            )
        )
    }

    private fun saveData_anything_to_remember() {
        var myshared_pref =
            this.activity!!.getSharedPreferences("myUpdate_pref", Context.MODE_PRIVATE)
        var editor = myshared_pref.edit()
        editor.putString("text_to_remember", et_anything_to_remember.text.toString())
        editor.apply()
    }

    private fun retrieveData_anything_to_remember() {
        var myshared_pref =
            this.activity!!.getSharedPreferences("myUpdate_pref", Context.MODE_PRIVATE)
        var text = myshared_pref.getString("text_to_remember", "")
        et_anything_to_remember.setText(text)
    }


}
