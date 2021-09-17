package com.example.movieslistinterview.ui.fragments.splashfragment

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import com.example.movieslistinterview.baseclasses.BaseViewModel
import com.example.movieslistinterview.utils.NetworkHelper
import com.example.movieslistinterview.utils.SingleLiveEvent

class SplashViewModel @ViewModelInject constructor(
) : BaseViewModel() {

    val btnClick = SingleLiveEvent<View>()



    fun onButtonClick(view: View): SingleLiveEvent<View> {
        btnClick.value = view
        return btnClick
    }

    fun fetchPostsFromApi() {

    }
}