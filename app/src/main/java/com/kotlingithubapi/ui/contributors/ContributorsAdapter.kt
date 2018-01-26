package com.kotlingithubapi.ui.contributors

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlingithubapi.R
import com.kotlingithubapi.model.Contributor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contributor.view.*


/**
 * Created by Valentyn on 16.07.2017.
 */
class ContributorsAdapter(private val context: Context,
                          var items: List<Contributor>?,
                          private val listener: ContributorClickListener?) : RecyclerView.Adapter<ContributorsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contributor = items?.get(position)

        Picasso.with(context).load(contributor?.avatarUrl).error(R.drawable.ic_github_placeholder).into(holder.ivAvatar)
        holder.tvLogin.text = contributor?.login
        ViewCompat.setTransitionName(holder.ivAvatar, contributor?.login)

        holder.itemView.setOnClickListener({
            listener?.onContributorClick(contributor, holder.ivAvatar)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_contributor, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = items?.size ?: 0

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar = itemView.iv_avatar
        val tvLogin = itemView.tv_login
    }

    interface ContributorClickListener {
        fun onContributorClick(contributor: Contributor?, viewAvatar: View?)
    }

}