package jaiyou.nfu.chiayitravel.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import jaiyou.nfu.chiayitravel.R
import jaiyou.nfu.chiayitravel.model.AttractionModel
import kotlinx.android.synthetic.main.layout_attraction_list_item.view.*

class AttractionAdapter (internal var context: Context, internal var attrList: List<AttractionModel>)
    : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionAdapter.AttractionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_attraction_list_item, parent, false)
        return AttractionViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return attrList.size
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bindModel(attrList[position])
    }

    class AttractionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val attr_name = itemView.attr_name
        val attr_address = itemView.attr_address
        val attr_image = itemView.attr_image

        fun bindModel(item: AttractionModel) {
            attr_name.text = item.getName()
            attr_address.text = item.getAdd()
            Picasso.get().load(item.getImage()).into(attr_image)
        }
    }

}