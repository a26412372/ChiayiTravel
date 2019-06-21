package jaiyou.nfu.chiayitravel.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.squareup.picasso.Picasso
import jaiyou.nfu.chiayitravel.R

import jaiyou.nfu.chiayitravel.model.ShopModel
import kotlinx.android.synthetic.main.layout_shop_list_item.view.*

class ShopListAdapter (val items: List<ShopModel>) : RecyclerView.Adapter<ShopListAdapter.ViewHolder>(){

    private var itemClickListener: OnItemClickListener? = null
    private val xxx: Int = 1234

    //入口
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.layout_shop_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClickListener!!.onItemClickListener(position)
        }
    }
    //返回數目
    override fun getItemCount(): Int {
        return items.size
    }

    //view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(item: ShopModel){
            //set description
            itemView.descriptionTextView.text = item.name

            Picasso.get().load(item.picture).into(itemView.imageView)

        }
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }

}