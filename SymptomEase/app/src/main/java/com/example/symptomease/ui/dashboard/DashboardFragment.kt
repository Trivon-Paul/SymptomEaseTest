package com.example.symptomease.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.symptomease.R
import com.example.symptomease.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var spinnerValue : String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val saver = this.activity?.getPreferences(AppCompatActivity.MODE_PRIVATE)
        saver?.let {
            val editor = saver.edit()
            // put a string set
            editor.apply()
        }

        var listSymptoms = mutableListOf<String>()

        listSymptoms.add("Fever")
        listSymptoms.add("cough")

        val spinner = root.findViewById<Spinner>(R.id.spinner)

        val symptomsAdapter = this.context?.let {
            ArrayAdapter<String>(it, android.R.layout.simple_spinner_dropdown_item, listSymptoms) }
        spinner.adapter = symptomsAdapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spinnerValue = listSymptoms.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        return root
    }

    fun onSendClick(view: View){
        if(spinnerValue == null){
            Toast.makeText(this.context, "Select a valid symptom", Toast.LENGTH_SHORT).show()
        } else {
            // send to next activity diagnostics
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}