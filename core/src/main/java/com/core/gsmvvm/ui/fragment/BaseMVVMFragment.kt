package com.core.gsmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseMVVMFragment<VB : ViewBinding>(private val bindingInflater: (LayoutInflater) -> VB) : Fragment() {

    private var _bindingView: VB? = null

    // This property is only valid between onCreateView and onDestroyView.
    open val bindingView: VB
        get() = _bindingView as VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _bindingView = bindingInflater.invoke(inflater)
        _bindingView?.let {
            return it.root
        } ?: throw IllegalArgumentException("Binding variable is null")
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        initViews(savedInstanceState)
        initListener()
    }

    open fun initViews(savedInstanceState: Bundle?) {}
    open fun initListener() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingView = null
    }
}