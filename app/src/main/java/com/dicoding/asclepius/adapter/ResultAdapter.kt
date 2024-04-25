package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.db.Cancer

class ResultAdapter(private var cancer: List<Cancer>) : RecyclerView.Adapter<ResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_list, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bindView(cancer[position])
    }

    override fun getItemCount(): Int {
        return cancer.size
    }

    fun setUserList(newResult: List<Cancer>) {
        cancer = newResult
        notifyDataSetChanged()
    }
}

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val ivAvatar: ImageView = itemView.findViewById(R.id.image)
    private val tvPrediction: TextView = itemView.findViewById(R.id.prediction)
    private val tvConfidence: TextView = itemView.findViewById(R.id.confidence)

    fun bindView(cancer: Cancer) {
        val confidence = cancer.confidence.toString().replace("%", "").toFloatOrNull() ?: 0f

        val formattedConfidence = String.format("%.2f%%", confidence)

        tvPrediction.text = cancer.prediction
        tvConfidence.text = formattedConfidence

        Glide.with(itemView)
            .load(cancer.imagePath)
            .into(ivAvatar)

    }
}
