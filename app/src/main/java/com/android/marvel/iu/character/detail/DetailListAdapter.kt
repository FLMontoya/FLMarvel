package com.android.marvel.iu.character.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.databinding.RowDetailBinding
import com.android.marvel.model.DetailItem
import com.squareup.picasso.Picasso


class DetailListAdapter : PagingDataAdapter<DetailItem, DetailItemViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: DetailItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailItemViewHolder {
        return DetailItemViewHolder(RowDetailBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<DetailItem>() {
        override fun areItemsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DetailItem, newItem: DetailItem): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class DetailItemViewHolder(private val rowDetailBinding: RowDetailBinding) :
    RecyclerView.ViewHolder(rowDetailBinding.root) {

    fun bind(detailItem: DetailItem) {
        rowDetailBinding.detailNameTextView.text = detailItem.name
        Picasso.get().load(detailItem.image).into(rowDetailBinding.detailImageView)
    }

}