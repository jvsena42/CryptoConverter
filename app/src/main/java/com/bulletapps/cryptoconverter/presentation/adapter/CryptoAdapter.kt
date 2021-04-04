package com.bulletapps.cryptoconverter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.cryptoconverter.data.model.CryptoModel
import com.bulletapps.cryptoconverter.databinding.ListCryptosBinding

class CryptoAdapter:RecyclerView.Adapter<CryptoAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListCryptosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val crypto = differ.currentList[position]
        holder.bind( crypto)
    }

    override fun getItemCount() = differ.currentList.size

    inner class NewsViewHolder(private val binding:ListCryptosBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(crypto: CryptoModel){

        }
    }

    private val callback = object :DiffUtil.ItemCallback<CryptoModel>(){
        override fun areItemsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
            return oldItem.abbreviation == newItem.abbreviation
        }

        override fun areContentsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,callback)
}