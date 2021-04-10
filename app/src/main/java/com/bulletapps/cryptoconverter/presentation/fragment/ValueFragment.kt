package com.bulletapps.cryptoconverter.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.bulletapps.cryptoconverter.R
import com.bulletapps.cryptoconverter.databinding.FragmentValueBinding
import com.bulletapps.cryptoconverter.presentation.activity.MainActivity
import com.bulletapps.cryptoconverter.presentation.viewmodel.MainActivityViewModel


class ValueFragment : Fragment() {

    private lateinit var binding: FragmentValueBinding
    private lateinit var mViewModel: MainActivityViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_value, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentValueBinding.bind(view)
        mViewModel = (activity as MainActivity).mainViewModel

        onClick()
    }

    override fun onResume() {
        super.onResume()
        initDropdown()
    }

    private fun onClick() {
        binding.btConvert.setOnClickListener {
            validation()
        }
    }

    private fun validation() {
        val valueText = binding.tieValue.text
        val fiat = binding.tieFiats.text
        if (valueText.isNullOrEmpty()){
            binding.tilValue.error = getString(R.string.enter_valid_number)
        }else{
            binding.tilValue.error = null
        }

        if (fiat.isNullOrEmpty()){
            binding.tilFiats.error = getString(R.string.please_select_currency)
        }else{
            binding.tilFiats.error = null
        }

        if (binding.tilFiats.error.isNullOrEmpty() && binding.tilValue.error.isNullOrEmpty()){

            mViewModel.amount = valueText.toString().toDouble()
            mViewModel.currency = fiat.toString()

            findNavController().navigate(R.id.action_valueFragment_to_resultFragment)
        }
    }

    private fun initDropdown() {
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, resources.getStringArray(R.array.fiats))
        (binding.tieFiats).setAdapter(adapter)
    }


}