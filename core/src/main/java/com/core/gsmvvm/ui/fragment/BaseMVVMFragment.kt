package com.core.gsmvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseMVVMFragment<B : ViewBinding> : Fragment() {
    lateinit var bindingView: B
    abstract fun getViewBinding(): B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingView = getViewBinding()
        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        initViews(savedInstanceState)
        initListener()
    }

    open fun initViews(savedInstanceState: Bundle?) {}
    open fun initListener() {}
}