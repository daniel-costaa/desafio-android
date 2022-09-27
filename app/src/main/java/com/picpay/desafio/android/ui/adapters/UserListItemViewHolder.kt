package com.picpay.desafio.android.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.UserDomain
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class UserListItemViewHolder(
    itemView: ListItemUserBinding
) : RecyclerView.ViewHolder(itemView.root) {

    private val name = itemView.name
    private val username = itemView.username
    private val picture = itemView.picture
    private val progressBar = itemView.progressBar

    fun bind(user: UserDomain) {
        name.text = user.name
        username.text = user.username
        progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user.img)
            .error(R.drawable.ic_round_account_circle)
            .into(picture, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.GONE
                }
            })
    }
}
