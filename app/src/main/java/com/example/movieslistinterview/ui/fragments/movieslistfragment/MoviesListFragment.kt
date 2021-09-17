package com.example.movieslistinterview.ui.fragments.movieslistfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieslistinterview.BR
import com.example.movieslistinterview.R
import com.example.movieslistinterview.baseclasses.BaseFragment
import com.example.movieslistinterview.data.models.MoviesListresponse
import com.example.movieslistinterview.databinding.FragmentMoviesListBinding
import com.example.movieslistinterview.network.APIInstance
import com.example.movieslistinterview.network.APIInterface
import com.example.movieslistinterview.ui.adapters.MoviesListAdapter
import com.example.movieslistinterview.ui.fragments.splashfragment.SplashViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

@AndroidEntryPoint
class MoviesListFragment : BaseFragment<FragmentMoviesListBinding, MoviesListViewModel>(),
    MoviesListAdapter.onItemClick {

    override val layoutId: Int
        get() = R.layout.fragment_movies_list
    override val viewModel: Class<MoviesListViewModel>
        get() = MoviesListViewModel::class.java
    override val bindingVariable: Int
        get() = BR.viewModel
    lateinit var dataList: MoviesListresponse
    private lateinit var adapter: MoviesListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun subscribeToViewLiveData() {
        super.subscribeToViewLiveData()
        linearLayoutManager = LinearLayoutManager(requireContext())
        mViewDataBinding.rvMoviesList!!.layoutManager = linearLayoutManager
        callAPI()
    }

    private fun callAPI() {
        showProgressBar()
        val request = APIInstance.buildService(APIInterface::class.java)
        val map = HashMap<String, String>()
        map["api_key"] = "0d0c8fe3f6ae1544a4d016ee10a67030"
        val call = request.moviesList(map)
//        Log.wtf("moviesListResponse",)
        call!!.enqueue(object : Callback<MoviesListresponse?> {
            override fun onResponse(
                call: Call<MoviesListresponse?>,
                response: Response<MoviesListresponse?>
            ) {
                hideProgressBar()
                val model: MoviesListresponse? = response.body()
                if (model != null) {
                    dataList = model
                    adapter = MoviesListAdapter(dataList, requireContext(), this@MoviesListFragment)
                    mViewDataBinding.rvMoviesList.adapter = adapter
                }
                adapter.notifyDataSetChanged()
                Log.wtf("moviesListResponse", "response: " + Gson().toJson(response.body()))
                Log.wtf(
                    "moviesListResponse",
                    "response: " + response.body()?.dates?.maximum
                )
            }

            override fun onFailure(call: Call<MoviesListresponse?>, t: Throwable) {
                hideProgressBar()
                Log.wtf("moviesListResponse", "error: " + t.message)
            }
        })
    }

    override fun click(position: Int, movieId: Int) {
        val args = Bundle()
        args.putString("movieId", movieId.toString())
        findNavController().navigate(R.id.action_moviesListFragment_to_moviesDetailFragment, args)
    }
}