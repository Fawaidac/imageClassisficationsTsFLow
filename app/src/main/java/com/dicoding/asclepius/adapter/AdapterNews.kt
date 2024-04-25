package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.model.Article

class AdapterNews(private var articles: List<Article>) :
    RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateData(newList: List<Article>) {
        articles = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_photo)
        private val textTitle: TextView = itemView.findViewById(R.id.text_title)
        private val textDesc: TextView = itemView.findViewById(R.id.text_desc)

        fun bind(article: Article) {
            textTitle.text = article.title
            textDesc.text = article.description

            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(imgPhoto)
        }
    }
}