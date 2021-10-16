package me.mayankchoudhary.usersapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.mayankchoudhary.usersapp.R
import me.mayankchoudhary.usersapp.databinding.SingleListItemBinding
import me.mayankchoudhary.usersapp.model.User

class UsersListAdapter : ListAdapter<User, UsersListAdapter.UsersViewHolder>(UserComparator()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val binding = SingleListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val currentItem  =  getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }

    class UsersViewHolder(private val binding: SingleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        val progressBar = ProgressBar(null, null, android.R.attr.progressBarStyleSmall)

        fun bind(user: User) {
            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar)
                    .placeholder(R.drawable.ic_baseline_replay_circle_filled_24)
                    .into(imageView)
                uid = user.uid
            }
        }
    }

    class UserComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem == newItem

    }
}