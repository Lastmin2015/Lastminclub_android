package ru.vsibi.presentation.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import ru.vsibi.presentation.R

open class BaseDialogFragment<Binding : ViewBinding>(private val inflate: Inflate<Binding>, @LayoutRes resId: Int) : DialogFragment(resId) {

    private var _binding: Binding? = null
    val binding: Binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.trans)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initViews()
        initArguments()
        initFragment()
        binding.initListeners()
        initData()
        initObservers()
    }

    open fun Binding.initViews() {
        log("initViews function not implemented!")
    }

    open fun initArguments() {
        log("initArguments function not implemented!")
    }

    open fun initFragment() {
        log("initFragment function not implemented!")
    }

    open fun Binding.initListeners() {
        log("initListeners function not implemented!")
    }

    open fun initData() {
        log("initData function not implemented!")
    }

    open fun initObservers() {
        log("initObservers function not implemented!")
    }

    fun log(message: String?) {
        Log.d(this.javaClass.simpleName, "$message")
    }

    fun popBack() {
        findNavController().popBackStack()
    }

    fun navigateUp() {
        findNavController().navigateUp()
    }

    fun toast(textRes: Int?) {
        textRes?.let {
            Toast.makeText(requireContext(), getString(textRes), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
