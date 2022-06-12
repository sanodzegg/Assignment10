package ge.edu.btu.task10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val items: ArrayList<Fire>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.action_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var event: TextView
        private lateinit var createDate: TextView

        fun onBind(item: Fire) {
            event = itemView.findViewById(R.id.event)
            createDate = itemView.findViewById(R.id.date)

            event.text = item.name
            createDate.text = item.date
        }
    }
}
