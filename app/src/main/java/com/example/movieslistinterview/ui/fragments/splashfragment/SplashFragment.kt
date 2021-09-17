package com.example.movieslistinterview.ui.fragments.splashfragment

import android.os.Bundle
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

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    val SPLASH_DELAY = 2_000L
    override val layoutId: Int
        get() = R.layout.fragment_splash
    override val viewModel: Class<SplashViewModel>
        get() = SplashViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel

    private val timer = Timer()
    private lateinit var timerTask: TimerTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()

        mViewModel.btnClick.observe(this, {
            when (it) {
                mViewDataBinding.tvNext -> {
                    navigateToActivity()
                }
                mViewDataBinding.tvNextOne -> {
                    navigateToActivity()
                }
            }
        })

//        timerTask = object : TimerTask() {
//            override fun run() {
//                Toast.makeText(context, "Toast", Toast.LENGTH_LONG).show()
////                navigateToActivity()
//            }
//        }
//        scheduleTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }

    private fun scheduleTimer() {
        timer.schedule(timerTask, SPLASH_DELAY)
    }

    private fun stopTimer() {
        try {
            timer.cancel()
            timer.purge()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun navigateToActivity() {
        findNavController().navigate(R.id.action_splashFragment_to_moviesListFragment)
    }
}