package com.sntsb.dexgo.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sntsb.dexgo.R

class LoadStateAdapter(
    private val mAction: OnAction
) : androidx.paging.LoadStateAdapter<LoadStateAdapter.LoadStateViewHolder>() {

    fun isLoading(loading: Boolean) {
        mAction.isLoading(loading)
    }

    interface OnAction {
        fun isLoading(loading: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.load_state_item, parent, false)
        return LoadStateViewHolder(view)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(loadState: LoadState) {
            isLoading(loadState is LoadState.Loading)
        }
    }

}