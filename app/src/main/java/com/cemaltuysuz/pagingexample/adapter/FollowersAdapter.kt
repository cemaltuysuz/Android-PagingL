package com.cemaltuysuz.pagingexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cemaltuysuz.pagingexample.R
import com.cemaltuysuz.pagingexample.databinding.RowBinding
import com.cemaltuysuz.pagingexample.model.UserItem


class FollowersAdapter() : RecyclerView.Adapter<FollowersAdapter.UserHolder>() {

    private val followersArray = ArrayList<UserItem>()

    class UserHolder(bind: RowBinding) : RecyclerView.ViewHolder(bind.root){
        var data = bind.user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RowBinding>(inflater,R.layout.row,parent,false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.data = followersArray[position]
    }

    override fun getItemCount(): Int {
        return followersArray.size
    }

    fun onDataChange(myList: ArrayList<UserItem>){
        this.followersArray.clear()
        this.followersArray.addAll(myList)
        notifyDataSetChanged()
    }
}