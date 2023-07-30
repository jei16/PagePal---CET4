import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagepal.databinding.PostedLendBooksBinding
import com.example.pagepal.models.LendBookData
import com.squareup.picasso.Picasso

class LendBooksInfoAdapter(private val infoList: ArrayList<LendBookData>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<LendBooksInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PostedLendBooksBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = infoList[position]
        holder.apply {
            binding.apply {
                postedBooktitle.text = currentItem.bookname
                postedAuthor.text = currentItem.author
                postedCaption.text = currentItem.caption
                Picasso.get().load(currentItem.imgUri).into(postedImage)
            }
            binding.root.setOnClickListener {
                itemClickListener.onItemClick(currentItem, position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: LendBookData, position: Int)
    }

    class ViewHolder(val binding: PostedLendBooksBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            // Optionally, you can handle click events here if preferred
        }
    }

    fun addItem(item: LendBookData) {
        infoList.add(item)
        notifyItemInserted(infoList.size - 1)
    }
    fun removeItem(position: Int) {
        infoList.removeAt(position)
        notifyItemRemoved(position)
    }
}