package com.example.movieslistinterview.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieslistinterview.R
import com.example.movieslistinterview.data.models.MoviesListresponse
import kotlinx.android.synthetic.main.movies_list_single_item.view.*

class MoviesListAdapter(
    var dataList: MoviesListresponse,
    val context: Context,
    val click: onItemClick
) : RecyclerView.Adapter<MoviesListAdapter.VHolder>() {
    private lateinit var linearLayoutManager: LinearLayoutManager

    class VHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvMovieTitle = v.tvMovieTitle
        val tvMoviedate = v.tvMovieDate
        val tvMovieAdultStatus = v.tvAdultStatus
        val ivMoviePoster = v.ivMoviePoster
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        val v: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.movies_list_single_item, parent, false)
        return MoviesListAdapter.VHolder(v)
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        Glide.with(context).load(dataList.results.get(position).poster_path)
            .into(holder.ivMoviePoster)
        holder.tvMovieTitle.setText(dataList.results.get(position).title)
        holder.tvMoviedate.setText(dataList.results.get(position).release_date)
        if (dataList.results.get(position).adult) {
            holder.tvMovieAdultStatus.setText("Adult")
        } else {
            holder.tvMovieAdultStatus.setText("Non Adult")
        }

        holder.itemView.setOnClickListener {
            click.click(
                position,
                dataList.results.get(position).id
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.results.size
    }

    interface onItemClick {
        fun click(position: Int, movieId: Int)
    }
}