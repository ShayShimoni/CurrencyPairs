package com.shay.currencypairs.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shay.currencypairs.data.entities.CurrencyPair
import com.shay.currencypairs.data.entities.CurrencyPairType
import com.shay.currencypairs.databinding.FragmentCurrencyPairsBinding
import kotlinx.coroutines.flow.collectLatest

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
        viewModel.subscribe()

        lifecycleScope.launchWhenStarted {
            viewModel.currencyPair.collectLatest { response ->
                if (response.isNotEmpty()){
                    CurrencyPairType.toEnum(response[3] as String)?.let {
                        val currencyPair = CurrencyPair(
                            pair = it,
                            price = ((response[1] as Map<*, *>)["a"] as List<*>)[0].toString()
                        )
                        currencyPair
                    }
                }
                Log.d("pttttt", "onViewCreated: $response")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}