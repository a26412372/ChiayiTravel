package jaiyou.nfu.chiayitravel.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jaiyou.nfu.chiayitravel.PostViewHolder
import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.model.Post

class PostAdapter (internal var context: Context, internal var postList: List<Post>)
    : RecyclerView.Adapter<PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindModel(postList[position])
    }

}