package com.shay.currencypairs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shay.currencypairs.databinding.FragmentCurrencyPairsBinding

class CurrencyPairsFragment : Fragment() {

    private var _binding: FragmentCurrencyPairsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyPairsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyPairsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}