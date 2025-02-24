package com.example.myapplication
import android.view.ViewGroup
import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import com.example.myapplication.databinding.PopularRvItemsBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

data class PopularItem(
    val img: String,
    val title: String,
    val ing: String
)
class PopularAdapter(var datalist: List<Recipe>, var context: Context) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: PopularRvItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularRvItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datalist[position]

        Glide.with(context).load(item.img).into(holder.binding.popularImg)
        holder.binding.popularTxt.text = item.title

        val time = item.ing.split("\n").filter { it.isNotEmpty() }
        if (time.isNotEmpty()) {
            holder.binding.popularTime.text = time[0]
        }
    }

    override fun getItemCount(): Int = datalist.size
}
