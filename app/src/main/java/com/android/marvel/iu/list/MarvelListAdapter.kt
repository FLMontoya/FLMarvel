package com.android.marvel.iu.list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.marvel.databinding.RowMarvelListItemBinding
import com.android.marvel.model.MarvelItem
import com.android.marvel.utils.ImageLoadingUtils
import com.bumptech.glide.Glide


class MarvelListAdapter(private val listener: MarvelListFragment.MarvelItemListener) :
    PagingDataAdapter<MarvelItem, MarvelListAdapter.MarvelItemViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MarvelItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelItemViewHolder {
        return MarvelItemViewHolder(
            RowMarvelListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
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

    inner class MarvelItemViewHolder(private val rowMarvelListItemBinding: RowMarvelListItemBinding) :
        RecyclerView.ViewHolder(rowMarvelListItemBinding.root) {

        fun bind(marvelItem: MarvelItem) {
            rowMarvelListItemBinding.apply {
                marvelItemNameTextView.text = marvelItem.name
                ImageLoadingUtils.load(marvelItem.image, marvelItemImageView)
                root.setOnClickListener {
                    listener.onClick(marvelItem)
                }
            }
        }
    }
}

