package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.db.Cancer
import com.dicoding.asclepius.viewmodel.CancerViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var cancerViewModel: CancerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cancerViewModel = ViewModelProvider(this).get(CancerViewModel::class.java)

        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val prediction = intent.getStringExtra(EXTRA_PREDICTION) ?: ""
        val confidenceString = intent.getStringExtra(EXTRA_CONFIDENCE) ?: "0%"
        val confidence = confidenceString.replace("%", "").toFloatOrNull() ?: 0f

        imageUri?.let {
            binding.resultImage.setImageURI(it)
        }

        val formattedConfidence = String.format("%.2f%%", confidence)
        binding.resultText.text = "Prediction: $prediction\nConfidence: $formattedConfidence"

        binding.saveButton.setOnClickListener{
            save(prediction, confidence)
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_PREDICTION = "extra_prediction"
        const val EXTRA_CONFIDENCE = "extra_confidence"
    }

    private fun save(prediction: String, confidence: Float){
        val imageUri = intent.getStringExtra(EXTRA_IMAGE_URI) ?: ""
        val cancer = Cancer(imageUri, prediction, confidence)
        cancerViewModel.insert(cancer)
        finish()
    }
}
