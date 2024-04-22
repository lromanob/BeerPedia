package it.demo.lromanob.beerbox.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import it.demo.lromanob.beerbox.databinding.ItemFilterBinding
import it.demo.lromanob.beerbox.models.db.Word
import javax.inject.Inject

class BeerFilterAdapter @Inject constructor() :
    ListAdapter<Word, BeerFilterAdapter.FilterViewHolder>(BeerComparator) {
    var filterClickListener: FilterClickListener? = null
    var filterLongClickListener: FilterLongClickListener? = null
    var currSelected :Int? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FilterViewHolder(
            ItemFilterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class FilterViewHolder(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.filterTitle.setOnClickListener {
                if(currSelected == absoluteAdapterPosition)
                    currSelected = null
                else
                    currSelected = absoluteAdapterPosition
                filterLongClickListener?.onFilterLongClicked(
                    binding, getItem(absoluteAdapterPosition) as Word)
                filterClickListener?.onFilterClicked(
                    binding, getItem(absoluteAdapterPosition) as Word)
            }
        }

        fun bind(item: Word) = with(binding) {

            filter = item
            root.isPressed = currSelected==absoluteAdapterPosition
        }
    }

    object BeerComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word) =
            oldItem.word == newItem.word

        override fun areContentsTheSame(oldItem: Word, newItem: Word) =
            oldItem.word == newItem.word
    }

    interface FilterClickListener {
        fun onFilterClicked(binding: ItemFilterBinding, filter: Word)
    }

    interface FilterLongClickListener {
        fun onFilterLongClicked(binding: ItemFilterBinding, filter: Word)
    }
}
