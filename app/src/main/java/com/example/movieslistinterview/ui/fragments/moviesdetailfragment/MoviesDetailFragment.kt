package com.example.movieslistinterview.ui.fragments.moviesdetailfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import com.bumptech.glide.Glide
import com.example.movieslistinterview.BR
import com.example.movieslistinterview.R
import com.example.movieslistinterview.baseclasses.BaseFragment
import com.example.movieslistinterview.data.models.MovieDetailResponse
import com.example.movieslistinterview.data.models.MoviesListresponse
import com.example.movieslistinterview.databinding.FragmentMoviesDetailBinding
import com.example.movieslistinterview.network.APIInstance
import com.example.movieslistinterview.network.APIInterface
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
    private var image_url: String = "https://image.tmdb.org/t/p/w500"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = arguments?.getString("movieId", "")
        callAPI()
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()
        Log.wtf("MovieID", movieId)
    }

    fun setValue(model: MovieDetailResponse?) {
        Glide.with(context).load(image_url + model?.poster_path)
            .into(mViewDataBinding.ivHeader)
        mViewDataBinding.tvMovieDetailsTitle.text = model?.title
        var genre: String = ""
        if (model != null) {
            for (i in model.genres.indices) {
                genre += if (i != model.genres.size - 1) {
                    model?.genres[i].name + ","
                } else {
                    model?.genres[i].name
                }
            }
            mViewDataBinding.tvMovieDetailsGenresValue.text = genre
        }
        mViewDataBinding.tvMovieDetailsReleaseDateValue.text = model?.release_date
        mViewDataBinding.tvMovieDetailsOverViewValue.text = model?.overview
        var rating: Float = 0.0F

        rating = model?.popularity?.toFloat()!!
        rating /= 500;
        mViewDataBinding.simpleRatingBar.rating = rating
    }

    private fun callAPI() {
        showProgressBar()
        val request = APIInstance.buildService(APIInterface::class.java)
        val map = HashMap<String, String>()
        map["api_key"] = "0d0c8fe3f6ae1544a4d016ee10a67030"

        val call = request.moviesListDetail(movieId, map)

        call!!.enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                hideProgressBar()
                val model: MovieDetailResponse = response.body()!!
                setValue(model)
                Log.wtf("moviesDetailResponse", "response: " + Gson().toJson(response.body()))
                Log.wtf(
                    "moviesDetailResponse",
                    "response: " + response.body()?.release_date
                )
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                hideProgressBar()
                Log.wtf("moviesDetailResponse", "error: " + t.message)
            }
        })
    }
}