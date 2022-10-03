package com.android.marvel.iu.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.databinding.RowDetailBinding
import com.android.marvel.model.MarvelItem
import com.android.marvel.utils.ImageLoadingUtils


class DetailListAdapter(private val detailItemListerner: DetailListFragment.DetailItemListerner) :
    PagingDataAdapter<MarvelItem, DetailItemViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: DetailItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailItemViewHolder {
        return DetailItemViewHolder(
            RowDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            detailItemListerner
        )
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MarvelItem>() {
        override fun areItemsTheSame(oldItem: MarvelItem, newItem: MarvelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarvelItem, newItem: MarvelItem): Boolean {
            return oldItem == newItem
        }
    }
}

class DetailItemViewHolder(
    private val rowDetailBinding: RowDetailBinding,
    private val listener: DetailListFragment.DetailItemListerner
) :
    RecyclerView.ViewHolder(rowDetailBinding.root) {

    fun bind(marvelItem: MarvelItem) {
        rowDetailBinding.apply {
            detailNameTextView.text = marvelItem.name
            root.setOnClickListener {
                listener.onClick(marvelItem)
            }
            ImageLoadingUtils.load(marvelItem.image, detailImageView)
        }
    }

}