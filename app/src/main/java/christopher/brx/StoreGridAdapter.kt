package christopher.brx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import christopher.brx.databinding.GridViewItemBinding

//constructor extends a ListAdapter with onclicklistener, StoresPropertyX, and a Viewholder
class StoreGridAdapter(val onClickListener: OnClickListener) : ListAdapter<StoresPropertyX, StoreGridAdapter.StoresPropertyViewHolder>(
    DiffCallback
) {

    //this creates the viewholder that our recyclerview needs and inflates the layout
    //Tells recycler view hope to create a new view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresPropertyViewHolder {
        return StoresPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /*Tell recycler view how to actually draw an item
    Takes two arguments, holder and position to bind
    Holder is a text item viewholder which is a generic type we specified on recycler view.adapter
    Position is just the index position in the list that we are supposed to be binding
    Need to update the views held by the viewholder held by the position*/

    override fun onBindViewHolder(holder: StoresPropertyViewHolder, position: Int) {
        val storesProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(storesProperty)
        }
        holder.bind(storesProperty)
    }

//this is the viewholder where the recyclerview acts all methods on, viewholders are objects that hold views
    //
    class StoresPropertyViewHolder (private var binding: GridViewItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(storesProperty: StoresPropertyX) {
            binding.property = storesProperty
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    //DiffCallback is a method that is more efficent bewcasue it checks recycler view for items that are not the same
    //and only updates those things in the recylerview instead of the whole list like with notifydatasetchanged()
    companion object DiffCallback : DiffUtil.ItemCallback<StoresPropertyX>() {
        override fun areItemsTheSame(oldItem: StoresPropertyX, newItem: StoresPropertyX): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StoresPropertyX, newItem: StoresPropertyX): Boolean {
            return oldItem.storeID == newItem.storeID
        }
    }

    class OnClickListener(val clickListener: (storesProperty: StoresPropertyX) -> Unit) {
        fun onClick(storesProperty: StoresPropertyX) = clickListener(storesProperty)
    }

    //needs to know how many items it's displaying
    //Has to return total number of items in data set held by the adapter
    //override fun getItemCount()
}