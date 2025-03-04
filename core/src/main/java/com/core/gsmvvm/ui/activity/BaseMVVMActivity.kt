package com.core.gsmvvm.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.viewbinding.ViewBinding

abstract class BaseMVVMActivity<B : ViewBinding> : AppCompatActivity() {
    lateinit var bindingView: B
    abstract fun getViewBinding(): B
    private var showBottomSystemUi = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomSystemUi()
        bindingView = getViewBinding()
        val view = bindingView.root
        setContentView(view)
        makeStatusBarTransparent()
        //
        initViews(savedInstanceState)
        initListener()
    }

    open fun setupBottomSystemUi(show: Boolean = true) {
        showBottomSystemUi = show
    }

    open fun initViews(savedInstanceState: Bundle?) {
        checkWindowReady(bindingView.root) {
            // setup toolbar
            setupToolbar(displayCutout())
        }

        bindingView.run {
            // setupView
            setupView(savedInstanceState)
        }
    }

    open fun setupView(savedInstanceState: Bundle?) {}

    open fun setupToolbar(marginTop: Int) {}

    open fun initListener() {}

    private fun makeStatusBarTransparent() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            decorView.systemUiVisibility = if (true) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                }
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            if (showBottomSystemUi) {
                window.insetsController?.show(WindowInsets.Type.navigationBars())
            }
        }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    open fun displayCutout(): Int {
        var height = 0
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val windowInsets = window.decorView.rootWindowInsets
                if (windowInsets != null) {
                    val displayCutout = windowInsets.displayCutout
                    if (displayCutout != null) {
                        height = displayCutout.safeInsetTop
                    }
                }
            }
        }
        return height
    }

    private fun checkWindowReady(rootView: View, onReady: () -> Unit) {
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { _, windowInsets ->
            onReady()
            windowInsets
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            if (showBottomSystemUi) return
            // Re-hide the navigation bar if the activity gains focus
            WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.navigationBars())
        }
    }

    private fun hideBottomSystemUI() {
        if (showBottomSystemUi) return
        // Create WindowInsetsControllerCompat instance
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)

        // Hide navigation bar initially
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

        // Ensure navigation bar hide behavior after being shown
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    override fun onResume() {
        super.onResume()
        hideBottomSystemUI()
    }
}