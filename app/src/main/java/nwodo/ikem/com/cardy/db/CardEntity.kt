package nwodo.ikem.com.cardy.db

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
 data class CardEntity @JvmOverloads constructor
    (val german: String, val english: String, @PrimaryKey(autoGenerate = true)val id: Int = 0){

 companion object{
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CardEntity>() {
     override fun areItemsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
        return oldItem.id == newItem.id
     }

     override fun areContentsTheSame(oldItem: CardEntity, newItem: CardEntity): Boolean {
       return oldItem == newItem
     }

    }
 }
}
