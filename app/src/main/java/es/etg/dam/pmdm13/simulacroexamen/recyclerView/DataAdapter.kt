package es.etg.dam.pmdm13.simulacroexamen.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.etg.dam.pmdm13.simulacroexamen.R

class DataAdapter(private val mList: List<ItemViewModel>): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_view_second_activity, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewModel = mList[position]
        holder.image.setImageResource(itemViewModel.image)
        holder.descp.text = itemViewModel.descipcion

        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, itemViewModel)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.imageViewTarjeta)
        val descp = itemView.findViewById<TextView>(R.id.textViewDescripcion)
    }

    private var onClickListener : OnClickListener ? = null
    interface OnClickListener {
        fun onClick(position: Int, model: ItemViewModel)
    }

    fun setOnClickListener(listener: OnClickListener){
        this.onClickListener = listener
    }
}