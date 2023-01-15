package com.shay.currencypairs.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shay.currencypairs.R
import com.shay.currencypairs.data.entities.CurrencyPair
import com.shay.currencypairs.data.entities.CurrencyPairType
import com.shay.currencypairs.databinding.FragmentCurrencyPairsBinding
import kotlinx.coroutines.flow.collectLatest

class CurrencyPairsFragment : Fragment() {

    private var _binding: FragmentCurrencyPairsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CurrencyPairsViewModel by viewModels()

    companion object {
        private var index = 0
    }

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

        val pair1 = binding.root.findViewById<CardView>(R.id.pair1)
        val pairName1 = binding.root.findViewById<TextView>(R.id.pair1_name)
        val pairPrice1 = binding.root.findViewById<TextView>(R.id.pair1_price)
        val pb1 = binding.root.findViewById<ProgressBar>(R.id.pb1)

        val pair2 = binding.root.findViewById<CardView>(R.id.pair2)
        val pairName2 = binding.root.findViewById<TextView>(R.id.pair2_name)
        val pairPrice2 = binding.root.findViewById<TextView>(R.id.pair2_price)
        val pb2 = binding.root.findViewById<ProgressBar>(R.id.pb2)

        val pair3 = binding.root.findViewById<CardView>(R.id.pair3)
        val pairName3 = binding.root.findViewById<TextView>(R.id.pair3_name)
        val pairPrice3 = binding.root.findViewById<TextView>(R.id.pair3_price)
        val pb3 = binding.root.findViewById<ProgressBar>(R.id.pb3)

        val pair4 = binding.root.findViewById<CardView>(R.id.pair4)
        val pairName4 = binding.root.findViewById<TextView>(R.id.pair4_name)
        val pairPrice4 = binding.root.findViewById<TextView>(R.id.pair4_price)
        val pb4 = binding.root.findViewById<ProgressBar>(R.id.pb4)

        val pair5 = binding.root.findViewById<CardView>(R.id.pair5)
        val pairName5 = binding.root.findViewById<TextView>(R.id.pair5_name)
        val pairPrice5 = binding.root.findViewById<TextView>(R.id.pair5_price)
        val pb5 = binding.root.findViewById<ProgressBar>(R.id.pb5)

        binding.root.apply {

            setTransition(R.id.t1)

            pair1.setOnClickListener {
                if (index == 0) return@setOnClickListener

                setTransition(R.id.t2)
                transitionToEnd()
                index = 0
            }

            pair2.setOnClickListener {
                if (index == 1) return@setOnClickListener

                if (index == 2) {
                    setTransition(R.id.t4)
                    transitionToEnd()
                } else {
                    setTransition(R.id.t1)
                    transitionToEnd()
                }
                index = 1
            }

            pair3.setOnClickListener {
                if (index == 2) return@setOnClickListener

                if (index == 3) {
                    setTransition(R.id.t6)
                    transitionToEnd()
                } else {
                    setTransition(R.id.t3)
                    transitionToEnd()
                }
                index = 2
            }

            pair4.setOnClickListener {
                if (index == 3) return@setOnClickListener

                if (index == 4) {
                    setTransition(R.id.t8)
                    transitionToEnd()
                } else {
                    setTransition(R.id.t5)
                    transitionToEnd()
                }
                index = 3
            }

            pair5.setOnClickListener {
                if (index == 4) return@setOnClickListener

                setTransition(R.id.t7)
                transitionToEnd()
                index = 4
            }
        }


        viewModel.subscribe()
        lifecycleScope.launchWhenStarted {
            viewModel.currencyPair.collectLatest { response ->
                if (response.isNotEmpty()) {
                    CurrencyPairType.toEnum(response[3] as String)?.let {
                        val currencyPair = CurrencyPair(
                            pair = it,
                            price = ((response[1] as Map<*, *>)["a"] as List<*>)[0].toString()
                        )

                        requireActivity().runOnUiThread {
                            when (currencyPair.pair) {
                                CurrencyPairType.XBT_EUR -> {
                                    pb1.isVisible = false
                                    pairName1.text = currencyPair.pair.value
                                    pairPrice1.text = currencyPair.price
                                }
                                CurrencyPairType.XBT_USD -> {
                                    pb2.isVisible = false
                                    pairName2.text = currencyPair.pair.value
                                    pairPrice2.text = currencyPair.price
                                }
                                CurrencyPairType.XBT_GBP -> {
                                    pb3.isVisible = false
                                    pairName3.text = currencyPair.pair.value
                                    pairPrice3.text = currencyPair.price
                                }
                                CurrencyPairType.ETH_EUR -> {
                                    pb4.isVisible = false
                                    pairName4.text = currencyPair.pair.value
                                    pairPrice4.text = currencyPair.price
                                }
                                CurrencyPairType.ETH_USD -> {
                                    pb5.isVisible = false
                                    pairName5.text = currencyPair.pair.value
                                    pairPrice5.text = currencyPair.price
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}