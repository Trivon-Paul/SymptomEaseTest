package com.example.symptomease

import android.os.Bundle
import android.util.Xml
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.symptomease.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var spinnerValue : String = ""
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_saved_symptoms, R.id.navigation_dashboard, R.id.navigation_diagnostics
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        retrofit = Retrofit.Builder()
            .baseUrl(uri)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        var listSymptoms = mutableListOf<String>()
        if(apiLogin()) {
            val symptomsList = retrofit.create(SymptomsListService::class.java)
            symptomsList.getAllSymptoms(token, "en-gb").enqueue(object : Callback<SymptomsList> {
                override fun onResponse(
                    call: Call<SymptomsList>,
                    response: Response<SymptomsList>
                ) {
                    if (response.body() != null) {
                        for (symptom in response.body()!!.listOfSymptoms) {
                            listSymptoms.add(symptom.symptomName)
                        }
                    }
                }

                override fun onFailure(call: Call<SymptomsList>, t: Throwable) {

                }

            })
        }

        val spinner = findViewById<Spinner>(R.id.spinner)
        val symptomsAdapter = this?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, listSymptoms) }
        spinner.adapter = symptomsAdapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerValue = listSymptoms.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }

    fun onSendClick(view: View){
        if(spinnerValue.equals("")){
            Toast.makeText(this, "Select a valid symptom", Toast.LENGTH_SHORT).show()
        } else {



            /*
            val condition : String
            val triage : String
            val specialist : String


            val builder = AlertDialog.Builder(this)
            builder.setTitle("Diagnostics")
            builder.setMessage("Symptom: $spinnerValue\n" +
                    "Condition: $condition\n" +
                    "Triage level: $triage\n" +
                    "Specialist Recommendation: $specialist")

            builder.setNeutralButton("Cancel"){dialog, _ ->
                dialog.cancel()
            }
            // create the dialog and show it
            val dialog = builder.create()
            dialog.show() */
        }
    }

    fun apiLogin(): Boolean{
        val login = retrofit.create(LoginService::class.java)
        var success = true

        login.login().enqueue(object: Callback<Login>{
            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                val body = response.body()
                if (body != null) {
                    token = body.Token
                }
                success = true
            }

            override fun onFailure(call: Call<Login>, t: Throwable) {
                success = false
            }

        })

        return success

    }

    companion object varables{
        const val uri = "https://sandbox-authservice.priaid.ch/"
        const val loginUri = "login"
        const val api_key = "pault@my.ccsu.edu"
        lateinit var token : String


        const val hash = "7da2b1034cd58b44b25fe4dd87a32695"
        const val hash2 = "3107d7589cfa06c71009935ead23dd9c"

        const val loginHeader = "Authorization: Bearer $api_key:$hash2"
    }
}