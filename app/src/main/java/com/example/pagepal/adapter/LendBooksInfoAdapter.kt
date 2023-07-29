import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagepal.databinding.PostedLendBooksBinding
import com.example.pagepal.models.LendBookData
import com.squareup.picasso.Picasso
import java.util.ArrayList

class LendBooksInfoAdapter(private val infoList: ArrayList<LendBookData>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<LendBooksInfoAdapter.ViewHolder>() {
    class ViewHolder(val binding: PostedLendBooksBinding) : RecyclerView.ViewHolder(binding.root)
    {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PostedLendBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
        }
        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClick(currentItem)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: LendBookData)
    }
}