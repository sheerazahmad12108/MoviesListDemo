package com.example.movieslistinterview.ui.fragments.splashfragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.movieslistinterview.BR
import com.example.movieslistinterview.R
import com.example.movieslistinterview.baseclasses.BaseFragment
import com.example.movieslistinterview.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_splash
    override val viewModel: Class<SplashViewModel>
        get() = SplashViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToActivity()
        }, 3000)
    }

    private fun navigateToActivity() {
        findNavController().navigate(R.id.action_splashFragment_to_moviesListFragment)
    }
}