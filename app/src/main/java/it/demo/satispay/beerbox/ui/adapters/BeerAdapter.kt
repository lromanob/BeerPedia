package it.demo.satispay.beerbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import it.demo.satispay.beerbox.databinding.ItemBeerBinding
import it.demo.satispay.beerbox.models.BeerItem
import javax.inject.Inject

class BeerAdapter @Inject constructor() :
    PagingDataAdapter<BeerItem, BeerAdapter.BeerViewHolder>(BeerComparator) {
    var beerClickListener: BeerClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BeerViewHolder(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class BeerViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.beerMoreInfo.setOnClickListener {
                beerClickListener?.onBeerClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as BeerItem
                )
            }
        }

        fun bind(item: BeerItem) = with(binding) {
            ViewCompat.setTransitionName(binding.beerTitle, "title_${item.id}")
            ViewCompat.setTransitionName(binding.beerType, "type_${item.id}")
            ViewCompat.setTransitionName(binding.beerDescription, "descr_${item.id}")
            ViewCompat.setTransitionName(binding.beerIcon, "icon_${item.id}")
            beer = item
        }
    }

    object BeerComparator : DiffUtil.ItemCallback<BeerItem>() {
        override fun areItemsTheSame(oldItem: BeerItem, newItem: BeerItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BeerItem, newItem: BeerItem) =
            oldItem == newItem
    }

    interface BeerClickListener {
        fun onBeerClicked(binding: ItemBeerBinding, beer: BeerItem)
    }
}