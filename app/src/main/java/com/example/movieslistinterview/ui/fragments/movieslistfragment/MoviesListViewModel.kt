package com.example.movieslistinterview.ui.fragments.movieslistfragment

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.example.movieslistinterview.baseclasses.BaseViewModel
import com.example.movieslistinterview.data.models.MoviesListresponse
import com.example.movieslistinterview.network.APIInstance
import com.example.movieslistinterview.network.APIInterface
import com.example.movieslistinterview.ui.adapters.MoviesListAdapter
import com.example.movieslistinterview.utils.NetworkHelper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesListViewModel @ViewModelInject constructor(
) : BaseViewModel() {
}