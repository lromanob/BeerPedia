package it.demo.lromanob.beerbox.ui.fragment

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import it.demo.lromanob.beerbox.R
import it.demo.lromanob.beerbox.databinding.FragmentBeerListBinding
import it.demo.lromanob.beerbox.databinding.ItemBeerBinding
import it.demo.lromanob.beerbox.databinding.ItemFilterBinding
import it.demo.lromanob.beerbox.models.BeerItem
import it.demo.lromanob.beerbox.models.db.Word
import it.demo.lromanob.beerbox.ui.adapters.BeerAdapter
import it.demo.lromanob.beerbox.ui.adapters.BeerFilterAdapter
import it.demo.lromanob.beerbox.ui.base.BaseFragment
import it.demo.lromanob.beerbox.ui.viewmodel.BeerListViewModel
import it.demo.lromanob.beerbox.utils.PagingLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class BeerListFragment : BaseFragment<FragmentBeerListBinding, BeerListViewModel>(),
    BeerAdapter.BeerClickListener {
    private val beerViewModel: BeerListViewModel by viewModels ()

    companion object {
        const val TAGNAME = "BeerListFragment"
    }

    @Inject
    lateinit var beerAdapter: BeerAdapter

    @Inject
    lateinit var filterAdapter: BeerFilterAdapter

    override val layoutId: Int
        get() = R.layout.fragment_beer_list

    override fun getVM(): BeerListViewModel = beerViewModel

    override fun bindVM(binding: FragmentBeerListBinding, vm: BeerListViewModel) =
        with(binding) {
            with(filterAdapter){
                rvBeersFilter.apply {
                    postponeEnterTransition()
                    viewTreeObserver.addOnPreDrawListener {
                        startPostponedEnterTransition()
                        true
                    }
                }
                rvBeersFilter.adapter = this
                with(vm) {
                    launchOnLifecycleScope {
                        launchOnLifecycleScope {
                            wordsFlow.collectLatest { submitList(it) }
                        }
                    }
                }
                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.DOWN) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val deletedCourse: Word = filterAdapter.currentList[viewHolder.bindingAdapterPosition]

                        vm.delete(deletedCourse)


                        Snackbar.make(binding.swipeRefresh, getString(R.string.element_deleted, deletedCourse.word) , Snackbar.LENGTH_LONG)
                            .setActionTextColor(ContextCompat.getColor(context!!, R.color.text_color_senape) )
                            .setAction(getString(R.string.undo)) {
                                vm.insert(deletedCourse)
                            }.show()

                    }
                }).attachToRecyclerView(rvBeersFilter)

                filterClickListener=object: BeerFilterAdapter.FilterClickListener {
                    override fun onFilterClicked(bind: ItemFilterBinding, filter: Word) {

                        bind.root.isPressed = !bind.root.isPressed
                        notifyDataSetChanged()
                        binding.searchView.setQuery(if(bind.root.isPressed) filter.word else "", true)
                    }
                }

            }
            with(beerAdapter) {
                rvBeersList
                    .apply {
                        postponeEnterTransition()
                        viewTreeObserver.addOnPreDrawListener {
                            startPostponedEnterTransition()
                            true
                        }
                    }
                rvBeersList.adapter = withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(this),
                    footer = PagingLoadStateAdapter(this)
                )
                val divider = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                ResourcesCompat.getDrawable(resources, R.drawable.divider,null)?.let { divider.setDrawable(it)  }
                rvBeersList.addItemDecoration(divider)

                swipeRefresh.setOnRefreshListener { refresh() }
                beerClickListener = this@BeerListFragment

                with(vm) {
                    launchOnLifecycleScope {
                        beers.collectLatest { submitData(it) }
                    }
                    launchOnLifecycleScope {
                        loadStateFlow.collectLatest {
                            swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                        }
                    }
                }
            }
            with(searchView){
                with(vm) {

                    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            setFilter(query)
                            return false
                        }

                        override fun onQueryTextChange(newText: String): Boolean {
                            setFilter(newText)
                            Log.i("BEERBOX_APP", "Llego al querytextchange")
                            return true
                        }
                    })
                }
            }
            with(addFilter){
                with(vm){
                    setOnClickListener {
                        searchView.query.toString().let {
                            if(!it.isNullOrEmpty()){
                                insert(Word(it))
                                Snackbar.make(binding.swipeRefresh, context.getString(R.string.added_2fav) , Snackbar.LENGTH_LONG).show()
                            }
                        }
                    }
                }
            }
        }

    override fun onBeerClicked(binding: ItemBeerBinding, beer: BeerItem) {
        val extras = FragmentNavigatorExtras(
            binding.beerTitle to "title_${beer.id}",
            binding.beerType to "type_${beer.id}",
            binding.beerDescription to "descr_${beer.id}",
            binding.beerIcon to "icon_${beer.id}"
        )
        findNavController().navigate(BeerListFragmentDirections.actionBeersToBeerDetail(beer), extras)
    }

}