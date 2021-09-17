package com.example.movieslistinterview.ui.fragments.moviesdetailfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieslistinterview.BR
import com.example.movieslistinterview.R
import com.example.movieslistinterview.baseclasses.BaseFragment
import com.example.movieslistinterview.data.models.MovieDetailResponse
import com.example.movieslistinterview.data.models.MoviesListresponse
import com.example.movieslistinterview.databinding.FragmentMoviesDetailBinding
import com.example.movieslistinterview.network.APIInstance
import com.example.movieslistinterview.network.APIInterface
import com.example.movieslistinterview.ui.adapters.MoviesListAdapter
import com.example.movieslistinterview.ui.fragments.movieslistfragment.MoviesListViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MoviesDetailFragment : BaseFragment<FragmentMoviesDetailBinding, MoviesDetailViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_movies_detail
    override val viewModel: Class<MoviesDetailViewModel>
        get() = MoviesDetailViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel
    var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getString("movieId", "")
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()
        Log.wtf("MovieID", movieId)
        callAPI()
    }

    private fun callAPI() {
        showProgressBar()
        val request = APIInstance.buildService(APIInterface::class.java)
        val map = HashMap<String, String>()
        map["api_key"] = "0d0c8fe3f6ae1544a4d016ee10a67030"

        val call = request.moviesListDetail("592863", map)

        call!!.enqueue(object : Callback<MovieDetailResponse?> {
            override fun onResponse(
                call: Call<MovieDetailResponse?>,
                response: Response<MovieDetailResponse?>
            ) {
                hideProgressBar()
                val model: MovieDetailResponse? = response.body()
                Log.wtf("moviesDetailResponse", "response: " + Gson().toJson(response.body()))
                Log.wtf(
                    "moviesDetailResponse",
                    "response: " + response.body()?.release_date
                )
            }

            override fun onFailure(call: Call<MovieDetailResponse?>, t: Throwable) {
                hideProgressBar()
                Log.wtf("moviesDetailResponse", "error: " + t.message)
            }
        })
    }
}