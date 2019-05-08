package jaiyou.nfu.chiayitravel

import android.support.v7.widget.RecyclerView
import android.view.View
import jaiyou.nfu.chiayitravel.model.Post
import kotlinx.android.synthetic.main.post_layout.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val txt_author = itemView.txt_author
    val txt_title = itemView.txt_title
    val txt_content = itemView.txt_content

    fun bindModel(post: Post) {
        txt_author.text = post.userId.toString()
        txt_title.text = post.title
        txt_content.text = StringBuilder(post.body.substring(0, 20)).append("...").toString()
    }
}