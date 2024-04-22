package it.demo.lromanob.beerbox.ui.fragment

import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import it.demo.lromanob.beerbox.R
import it.demo.lromanob.beerbox.databinding.FragmentBeerDetailBottomsheetBinding
import it.demo.lromanob.beerbox.ui.base.BaseBSFragment
import it.demo.lromanob.beerbox.ui.viewmodel.BeerDetailViewModel

class BeerDetailBottomsheetFragment : BaseBSFragment<FragmentBeerDetailBottomsheetBinding, BeerDetailViewModel>(){
    private val beerDetailViewModel: BeerDetailViewModel by viewModels()
    val args: BeerDetailBottomsheetFragmentArgs by navArgs()
    override val layoutId: Int
        get() = R.layout.fragment_beer_detail_bottomsheet


    override fun getVM(): BeerDetailViewModel = beerDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }
    }

    override fun bindVM(binding: FragmentBeerDetailBottomsheetBinding, vm: BeerDetailViewModel) {
        with(binding) {
            beer = args.beer
            ViewCompat.setTransitionName(binding.beerTitle, "title_${args.beer.id}")
            ViewCompat.setTransitionName(binding.beerType, "type_${args.beer.id}")
            ViewCompat.setTransitionName(binding.beerDescription, "descr_${args.beer.id}")
            ViewCompat.setTransitionName(binding.beerIcon, "icon_${args.beer.id}")
        }
    }



}