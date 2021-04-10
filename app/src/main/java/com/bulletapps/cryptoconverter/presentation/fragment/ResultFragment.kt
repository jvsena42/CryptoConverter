package com.bulletapps.cryptoconverter.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bulletapps.cryptoconverter.R
import com.bulletapps.cryptoconverter.data.listener.ItemClickListener
import com.bulletapps.cryptoconverter.data.util.*
import com.bulletapps.cryptoconverter.data.util.Constants.CRYPTO_ID
import com.bulletapps.cryptoconverter.data.util.Constants.MAIN_FIAT
import com.bulletapps.cryptoconverter.databinding.FragmentResultBinding
import com.bulletapps.cryptoconverter.presentation.activity.MainActivity
import com.bulletapps.cryptoconverter.presentation.adapter.CryptoAdapter
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    @Inject
    lateinit var adapter: CryptoAdapter
    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = (activity as MainActivity).mainViewModel
        binding = FragmentResultBinding.bind(view)

        initRecyclerView()
        getValues()
        observe()
        updateTextView()

        GlobalScope.launch {
            onRefresh()
        }
    }

    private suspend fun onRefresh() {
        GlobalScope.launch {
            delay(1500)

            binding.srLayout.setOnRefreshListener {
                getValues()
                binding.srLayout.isRefreshing = false
            }
        }

    }

    private fun updateTextView() {
        binding.tvFiat.text = "${mViewModel.amount} ${mViewModel.currency}"
    }

    private fun initRecyclerView() {
        binding.rvResult.adapter = adapter
        binding.rvResult.setHasFixedSize(true)
        binding.rvResult.layoutManager = LinearLayoutManager(requireContext())
        adapter.attachListener(object : ItemClickListener<String> {
            override fun onClick(item: String) {
                binding.root.showSnackbar(getString(R.string.value_copied_clipboard))
                requireContext().copyToClipboard(item)
            }

        })
        adapter.setAmount(mViewModel.amount)
        adapter.setCurrency(mViewModel.currency)
    }

    private fun getValues() {
        mViewModel.getValues(CRYPTO_ID, MAIN_FIAT)
    }

    private fun observe() {
        mViewModel.listCrypto.observe(viewLifecycleOwner, {
             when(it){
                 is Resource.Loading->{
                     binding.progressBar.viewVisible()
                 }
                 is Resource.Success->{
                     binding.progressBar.viewGone()
                     it.data?.let {
                         adapter.differ.submitList(mViewModel.createList(it))
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

}