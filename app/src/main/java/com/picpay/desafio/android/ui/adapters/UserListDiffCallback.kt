package com.picpay.desafio.android.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.picpay.desafio.android.data.model.UserDomain

class UserListDiffCallback(
    private val oldList: List<UserDomain>,
    private val newList: List<UserDomain>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}
