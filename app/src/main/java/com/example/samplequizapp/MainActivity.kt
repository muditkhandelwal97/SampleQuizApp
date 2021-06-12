package com.example.samplequizapp

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.samplequizapp.`interface`.LetterSelectedCallback
import com.example.samplequizapp.adapter.JumbledWordsAdapter
import com.example.samplequizapp.models.Logo
import com.example.samplequizapp.policies.DefaultTimeBasedScoringPolicy
import com.example.samplequizapp.policies.ScoringPolicy
import com.example.samplequizapp.viewmodel.LogoViewModel
import java.util.*

class MainActivity : AppCompatActivity(), LetterSelectedCallback {

    private var logoList: ArrayList<Logo>? = null
    private var currIndex = -1
    private lateinit var scoringPolicy: ScoringPolicy
    private var timer: Timer? = null
    private var timeLeft: Double = 0.0

    private lateinit var logoImgView: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var selectedTextView: TextView

    private val totalTimePerQues = 10.0
    private var currentLogo: Logo? = null
    private var totalScore = 0
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var gridAdapter: JumbledWordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViews()
        scoringPolicy = DefaultTimeBasedScoringPolicy()
        val genericViewModel = ViewModelProvider(this).get(LogoViewModel::class.java)
        observeViewModel(genericViewModel)
    }

    private fun setUpViews() {
        logoImgView = findViewById(R.id.logoImgView)
        recyclerView = findViewById(R.id.recyclerView)
        selectedTextView = findViewById(R.id.selectedText)
        gridLayoutManager = GridLayoutManager(this, 10)
        recyclerView.layoutManager = gridLayoutManager
        gridAdapter = JumbledWordsAdapter(
            this, CharArray(0),
            this)
        recyclerView.adapter = gridAdapter
    }

    private fun observeViewModel(logoViewModel: LogoViewModel) {
        val path: Uri =
            Uri.parse("android.resource://" + applicationContext.packageName + "/raw/logo")

        logoViewModel.getData(path.toString())?.observe(this,
            Observer<ArrayList<Logo>> {
                logoList = it
                resetLevel()
            })
    }

    private fun resetLevel() {
        if (logoList.isNullOrEmpty()) {
            Toast.makeText(this, "No logo available. Pls retry later", Toast.LENGTH_LONG).show()
            return
        }
        currIndex = -1
        setUpNextQues()
    }

    private fun setUpNextQues() {
        currIndex++
        if (logoList.isNullOrEmpty()) {
            Toast.makeText(this, "No logo available. Pls retry later", Toast.LENGTH_LONG).show()
            return
        }
        logoList?.let {
            if (currIndex > it.size -1) {
                Toast.makeText(this, "No more logo available", Toast.LENGTH_LONG).show()
                return
            }
            currentLogo = it[currIndex]
            gridAdapter.setLettersList(it[currIndex].name.toCharArray())
        }

        selectedTextView.text = ""
        resetTimer(totalTimePerQues)
    }

    private fun resetTimer(timeLeft: Double) {
        //Todo timer implementation
    }

    override fun onLetterSelected(letter: Char) {
        val selectedString = selectedTextView.text.toString()
        val newSelectedText = selectedString + letter
        selectedTextView.text = newSelectedText
        if (selectedString.length == currentLogo?.name?.length) {
            if (TextUtils.equals(selectedString, currentLogo?.name)) {
                totalScore += scoringPolicy.calcualteScore(timeLeft = timeLeft, totalTime = totalTimePerQues)
                setUpNextQues()
            }
        }
    }


}