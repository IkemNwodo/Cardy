package nwodo.ikem.com.cardy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nwodo.ikem.com.cardy.R
import nwodo.ikem.com.cardy.db.CardEntity
import nwodo.ikem.com.cardy.utils.CardyClickListener

class CardyAdapter(val cardyClickListener: CardyClickListener) :
    ListAdapter<CardEntity, CardyAdapter.CardyViewHolder>(CardEntity.DIFF_CALLBACK) {


    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): CardyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_card, parent, false)
        return CardyViewHolder(v)
    }

    override fun onBindViewHolder(@NonNull holder: CardyViewHolder, position: Int) {
        val item = getItem(position)

        holder.german.text = item.german
        holder.english.text = item.english
    }

    fun getCardyAtPosition(position: Int): CardEntity = getItem(position)

    inner class CardyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener,
        View.OnLongClickListener {

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
        var german: TextView = itemView.findViewById(R.id.german)
        var english: TextView = itemView.findViewById(R.id.english)


        override fun onLongClick(v: View?): Boolean {
            cardyClickListener.onCardyLongClickListener(getItem(adapterPosition).id)
            return true
        }

        override fun onClick(v: View?) {
            cardyClickListener.onCardyClickListener(getItem(adapterPosition).id)
        }
    }
}