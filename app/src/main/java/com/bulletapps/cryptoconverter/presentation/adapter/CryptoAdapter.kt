package com.bulletapps.cryptoconverter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.cryptoconverter.databinding.ListCryptosBinding

//class CryptoAdapter:RecyclerView.Adapter<CryptoAdapter.NewsViewHolder>() {
//
//    private val callback = object :DiffUtil.ItemCallback<Article>(){
//        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem.url == newItem.url
//        }
//
//        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem == newItem
//        }
//    }
//
//    val differ = AsyncListDiffer(this,callback)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val binding = ListCryptosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return NewsViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//        val article = differ.currentList[position]
//        holder.bind( article)
//    }
//
//    override fun getItemCount() = differ.currentList.size
//
//    inner class NewsViewHolder(private val binding:ListCryptosBinding):RecyclerView.ViewHolder(binding.root){
//        fun bind(article: Article){
//
//
//        }
//    }
//}