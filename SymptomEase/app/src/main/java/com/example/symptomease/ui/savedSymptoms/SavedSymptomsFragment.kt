package com.example.symptomease.ui.savedSymptoms

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

class SavedSymptomsFragment : Fragment() {

    private var _binding: FragmentSavedSymptomsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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

        val savedSymptoms = mutableSetOf<String>()
        val savedSymptomsDescription = mutableSetOf<String>()

        val saver = this.activity?.getPreferences(AppCompatActivity.MODE_PRIVATE)
        saver?.let {
            saver.getStringSet("savedSymptomsName", savedSymptoms)
            saver.getStringSet("savedSymptomsDescription", savedSymptomsDescription)
        }

        savedSymptoms.add("Fever")
        savedSymptomsDescription.add("Body core temperature is high")

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