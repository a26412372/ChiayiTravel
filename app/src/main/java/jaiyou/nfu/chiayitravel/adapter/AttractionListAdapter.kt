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

class AttractionListAdapter (val context: Context, val attrList: List<AttractionModel>)
    : RecyclerView.Adapter<AttractionListAdapter.AttractionListViewHolder>(){

    private var itemClickListener: AttractionListAdapter.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionListAdapter.AttractionListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_attraction_list_item, parent, false)
        return AttractionListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return attrList.size
    }

    override fun onBindViewHolder(holder: AttractionListViewHolder, position: Int) {
        holder.bindModel(attrList[position])
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }

    inner class AttractionListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val attr_name = itemView.attr_name
        val attr_address = itemView.attr_address
        val attr_image = itemView.attr_image

        fun bindModel(item: AttractionModel) {
            attr_name.text = item.name
            attr_address.text = item.address
            Picasso.get().load(item.picture).into(attr_image)
        }
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }

}