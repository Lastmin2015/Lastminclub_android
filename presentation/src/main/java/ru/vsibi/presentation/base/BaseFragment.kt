package ru.vsibi.presentation.base

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import ru.vsibi.helper.IError
import ru.vsibi.presentation.R
import ru.vsibi.presentation.helpers.Router

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

open class BaseFragment<Binding : ViewBinding>(private val inflate: Inflate<Binding>, @LayoutRes resId: Int) : Fragment(resId), View.OnClickListener {

    private var _binding: Binding? = null
    val binding: Binding get() = _binding!!
    val router : Router by lazy { Router.instance }
    val TAG = this.javaClass.simpleName
    var toolbarTitle: TextView? = null
    var ivBack: ImageView? = null
    var tvError: TextView? = null
    var navHostFragment: NavHostFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDefaultToolbar()
        binding.initViews()
        initArguments()
        initFragment()
        binding.initListeners()
        initData()
        initObservers()
    }

    private fun initDefaultToolbar() {
        (activity as AppCompatActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(false)
        }
    }

    open fun Binding.initViews() {
        log("setupUI function not implemented!")
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

    fun navigate(resId: Int) {
        findNavController().safeNavigate(resId, null, null)
    }

    fun navigate(resId: Int, arguments: Bundle) {
        findNavController().safeNavigate(resId, arguments, null)
    }

    fun popBack() {
        findNavController().popBackStack()
    }

    fun toast(text: String) {
        Toast.makeText(requireContext(), "$text", Toast.LENGTH_SHORT).show()
    }

    fun snack(text: String) {
        Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG)
            .show()
    }

    fun toast(textRes: Int?) {
        textRes?.let {
            Toast.makeText(requireContext(), getString(textRes), Toast.LENGTH_SHORT).show()
        }
    }

    fun longToast(text: String) {
        Toast.makeText(requireContext(), "$text", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {

    }

    fun onError(errorMessage: String) {
        toast(errorMessage)
//        tvError?.setText(errorMessage)
//        tvError?.visible()
    }

    open fun onError(error: IError?) {
        if (error == null) return
        if (error.getErrorResource() != null) {
            val message = getString(error.getErrorResource()) + " " + error.getErrorCode() + " " + error.getErrorMessage()
            toast(message)
            log(message)
        } else {
            error.getErrorMessage()?.let {
                toast(it)
            }
        }
        if(error.getErrorCode() == 401){
            router.mainFragmentInstance?.clearAuth()
            router.reopenApp()
        }
    }

    fun NavController.safeNavigate(actionId: Int, bundle: Bundle?, extras: Navigator.Extras?) {
        this.apply {
            val action = currentDestination?.getAction(actionId) ?: graph.getAction(actionId)
            if (action != null && currentDestination?.id != action.destinationId) {
                navigate(actionId, bundle, null, extras)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}