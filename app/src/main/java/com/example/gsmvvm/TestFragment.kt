package com.example.gsmvvm

import android.os.Bundle
import com.core.gsmvvm.ui.fragment.BaseMVVMFragment
import com.example.gsmvvm.databinding.FragmentTestBinding

class TestFragment : BaseMVVMFragment<FragmentTestBinding>(FragmentTestBinding::inflate) {

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

    }
}