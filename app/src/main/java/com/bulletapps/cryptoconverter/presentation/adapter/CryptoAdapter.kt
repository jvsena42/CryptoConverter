package com.bulletapps.cryptoconverter.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bulletapps.cryptoconverter.data.listener.ItemClickListener
import com.bulletapps.cryptoconverter.data.model.CryptoModel
import com.bulletapps.cryptoconverter.data.util.Constants
import com.bulletapps.cryptoconverter.databinding.ListCryptosBinding
import java.util.*

class CryptoAdapter:RecyclerView.Adapter<CryptoAdapter.NewsViewHolder>() {

    private lateinit var mListener: ItemClickListener<String>
    private var amount:Double = 0.0
    private var currency:String = ""

    fun setAmount(value:Double){
        amount = value
    }

    fun setCurrency(value:String){
        currency = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ListCryptosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val crypto = differ.currentList[position]
        holder.bind( crypto)
    }

    fun attachListener(listener: ItemClickListener<String>) {
        mListener = listener
    }

    override fun getItemCount() = differ.currentList.size

    inner class NewsViewHolder(private val binding:ListCryptosBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(crypto: CryptoModel){

            var value: Double =0.0

            when(currency){
                Constants.FIAT_USD->{
                    value = amount/ crypto.usd!!
                }
                Constants.FIAT_EUR->{
                    value = amount/ crypto.eur!!
                }
                Constants.FIAT_BRL->{
                    value = amount/ crypto.brl!!
                }
                Constants.FIAT_ARS->{
                    value = amount/ crypto.ars!!
                }
                Constants.FIAT_MXN->{
                    value = amount/ crypto.mxn!!
                }
            }

            val valueString = "%.8f".format(value).replace(",",".",true)

            binding.tvValue.text = valueString
            binding.btCopy.setOnClickListener {
                mListener.onClick(valueString)
            }
            binding.tvAbbreviation.text = crypto.abbreviation
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