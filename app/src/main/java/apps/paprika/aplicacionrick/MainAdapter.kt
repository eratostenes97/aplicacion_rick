package apps.paprika.aplicacionrick

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import apps.paprika.aplicacionrick.network.Character
import coil.load
import coil.transform.CircleCropTransformation
import java.security.AccessController.getContext

class MainAdapter(val charactersList: List<Character>):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
       mListener = clickListener
    }


inner class MainViewHolder(private val itemView:View, clickListener: onItemClickListener):RecyclerView.ViewHolder(itemView){
    init {
        itemView.setOnClickListener {
            clickListener.onItemClick(adapterPosition)
        }
    }


    fun bindData(character: Character){
        val name = itemView.findViewById<TextView>(R.id.name)
        val image = itemView.findViewById<ImageView>(R.id.image)
    name.text = character.name
        image.load(character.image){
         transformations(CircleCropTransformation())
        }
    }


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return MainViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    private fun goToDetail(){

    }
    override fun getItemCount(): Int = charactersList.size
}