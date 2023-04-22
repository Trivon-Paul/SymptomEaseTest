package com.example.symptomease.ui.savedSymptoms

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.symptomease.MyAdapter
import com.example.symptomease.R
import com.example.symptomease.databinding.FragmentSavedSymptomsBinding
import com.example.symptomease.ui.dashboard.SymptomDatabase

class SavedSymptomsFragment : Fragment() {

    private var _binding: FragmentSavedSymptomsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dbHelper : SQLiteOpenHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(SavedSymptomsViewModel::class.java)

        _binding = FragmentSavedSymptomsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        dbHelper = SymptomDatabase(root.context)

        val savedSymptoms = mutableListOf<String>()
        val savedSymptomsDescription = mutableListOf<String>()

        val cursor = (dbHelper as SymptomDatabase).viewAllSymptoms

        while(cursor.moveToNext()){
            savedSymptoms.add(cursor.getString(1))
            savedSymptomsDescription.add(cursor.getString(2))
        }

        val recycler = root.findViewById<RecyclerView>(R.id.symptomsList)

        recycler.adapter = MyAdapter(savedSymptoms, savedSymptomsDescription)

        recycler.layoutManager = LinearLayoutManager(this.context)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}