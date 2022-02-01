package com.example.feature_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.api.MyCharacter

interface UserListAdapterClicks {
    fun onItemClick(model: MyCharacter)
}

class CharacterAdapter: ListAdapter<MyCharacter, RecyclerView.ViewHolder>(CharacterItemCallback()) {

    private var userListAdapterClicks: UserListAdapterClicks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as DefaultViewHolder
        viewHolder.nameCharacter.text = currentList[position].name
        Glide
            .with(viewHolder.itemView)
            .asBitmap()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .load(currentList[position].image)
            .into(viewHolder.imageCharacter)

        viewHolder.itemView.setOnClickListener {
            userListAdapterClicks?.onItemClick(currentList[position])
        }
    }

    fun attachClicks(userListAdapterClicks: UserListAdapterClicks) {
        this.userListAdapterClicks = userListAdapterClicks
    }
}

internal class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageCharacter: ImageView = itemView.findViewById(R.id.imageCharacter)
    val nameCharacter: TextView = itemView.findViewById(R.id.nameCharacter)
}

private class CharacterItemCallback : DiffUtil.ItemCallback<MyCharacter>() {

    override fun areItemsTheSame(oldItem: MyCharacter, newItem: MyCharacter): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MyCharacter, newItem: MyCharacter): Boolean {
        return oldItem.id == newItem.id
    }
}