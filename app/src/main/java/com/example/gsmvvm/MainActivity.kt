package com.example.gsmvvm

import com.core.gsmvvm.ui.activity.BaseMVVMActivity
import com.example.gsmvvm.databinding.ActivityMainBinding

class MainActivity : BaseMVVMActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}