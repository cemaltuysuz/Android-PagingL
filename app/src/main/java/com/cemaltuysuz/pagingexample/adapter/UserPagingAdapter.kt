package com.cemaltuysuz.pagingexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.databinding.RowBinding
import com.cemaltuysuz.pagingexample.model.UserItem
import javax.inject.Inject

class UserPagingAdapter @Inject constructor() :
    PagingDataAdapter<UserItem,UserViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>(){
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
               return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder:UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.binding.user = current
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowBinding>(inflater, R.layout.row,parent,false)
        return UserViewHolder(view)
    }

}