package com.bulletapps.cryptoconverter.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulletapps.cryptoconverter.R
import com.bulletapps.cryptoconverter.data.listener.ItemClickListener
import com.bulletapps.cryptoconverter.data.util.*
import com.bulletapps.cryptoconverter.data.util.Constants.CRYPTO_ID
import com.bulletapps.cryptoconverter.data.util.Constants.MAIN_FIAT
import com.bulletapps.cryptoconverter.databinding.ActivityMainBinding
import com.bulletapps.cryptoconverter.presentation.adapter.CryptoAdapter
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModel
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MainActivityViewModelFactory
    lateinit var viewModel:MainActivityViewModel
    @Inject
    lateinit var adapter: CryptoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initRecyclerView()
        onClick()
        observe()
    }

    private fun observe() {
        viewModel.listCrypto.observe(this, {
            when(it){
                is Resource.Loading->{
                    binding.progressBar.viewVisible()
                }
                is Resource.Success->{
                    binding.progressBar.viewGone()
                    it.data?.let {
                        adapter.differ.submitList(viewModel.createList(it))
                    }
                }
                is Resource.Error ->{
                    it.message?.let {
                        binding.progressBar.viewGone()
                        binding.root.showSnackbarRed(it)
                    }
                }
            }
        })
    }

    private fun onClick() {
        binding.btConvert.setOnClickListener {
            validation()
        }
    }

    private fun validation() {
        val valueText = binding.tieValue.text
        if (valueText.isNullOrEmpty()){
            binding.tilValue.error = getString(R.string.enter_valid_number)
        }else{
            binding.tilValue.error = null
        }
        if (binding.tilValue.error.isNullOrEmpty()){
            getValues()
        }
    }

    private fun getValues() {
        viewModel.getValues(CRYPTO_ID, MAIN_FIAT)
    }

    private fun initRecyclerView() {
        binding.rvValues.adapter = adapter
        binding.rvValues.setHasFixedSize(true)
        binding.rvValues.layoutManager = LinearLayoutManager(this)
        adapter.attachListener(object :ItemClickListener<String> {
            override fun onClick(item: String) {
                binding.root.showSnackbar(getString(R.string.value_copied_clipboard))
                copyToClipboard(item)
            }

        })
    }
}