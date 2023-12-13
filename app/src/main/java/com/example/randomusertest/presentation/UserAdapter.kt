package com.example.randomusertest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomusertest.R
import com.example.randomusertest.databinding.UserItemListBinding
import com.example.randomusertest.domain.User

class UserAdapter(
    private var users: List<User>,
    private val onUserCLick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, onUserCLick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    fun updateData(newUsers: List<User>) {
        val currentList = users.toMutableList()
        currentList.addAll(newUsers)
        users = currentList
        notifyDataSetChanged()
    }

    class UserViewHolder(
        private val binding: UserItemListBinding,
        val onUserClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                userFullName.text = "${user.name.title} ${user.name.first} ${user.name.last}"
                userEmail.text = user.email
                Glide.with(userImage.context)
                    .load(user.picture.large)
                    .circleCrop()
                    .into(userImage)
                Glide.with(arrow.context)
                    .load(R.drawable.small)
                    .into(arrow)
                root.setOnClickListener {
                    onUserClick(user)
                }
            }
        }
    }
}