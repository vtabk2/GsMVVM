package com.example.gsmvvm

import android.graphics.Color
import android.os.Bundle
import com.core.gsmvvm.ui.activity.BaseMVVMActivity
import com.example.gsmvvm.databinding.ActivityMainBinding

class MainActivity : BaseMVVMActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun setupView(savedInstanceState: Bundle?) {
        super.setupView(savedInstanceState)

        val tag = TestFragment::class.java.simpleName
        val testFragment = TestFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flTest, testFragment, tag)
            .addToBackStack(tag)
            .commit()

        bindingView.flTest.setBackgroundColor(Color.RED)
    }
}