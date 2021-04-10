package com.bulletapps.cryptoconverter.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bulletapps.cryptoconverter.R
import com.bulletapps.cryptoconverter.databinding.ActivityMainBinding
import com.bulletapps.cryptoconverter.presentation.adapter.CryptoAdapter
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModel
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModelFactory
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: MainActivityViewModelFactory
    lateinit var mainViewModel:MainActivityViewModel
    @Inject
    lateinit var adapter: CryptoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        initAds()
    }

    private fun initAds() {
        MobileAds.initialize(this) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}