package it.demo.lromanob.beerbox.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import it.demo.lromanob.beerbox.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import it.demo.lromanob.beerbox.R
import it.demo.lromanob.beerbox.databinding.ActivityMainBinding
import it.demo.lromanob.beerbox.ui.fragment.BeerListFragment
import it.demo.lromanob.beerbox.ui.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM(): MainViewModel = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)


        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM or ActionBar.DISPLAY_SHOW_HOME

        }

        val navHostFragment = obtainNavHostFragment(supportFragmentManager, BeerListFragment.TAGNAME, R.navigation.main_navigation, R.id.fragmentContainerView)
        supportFragmentManager.beginTransaction()
            .attach(navHostFragment)
            .apply {
                setPrimaryNavigationFragment(navHostFragment)
            }
            .commitNow()

        supportFragmentManager.addOnBackStackChangedListener {
            if(supportFragmentManager.backStackEntryCount> 1)
                supportFragmentManager.popBackStackImmediate()
        }

    }

    private fun obtainNavHostFragment(
        fragmentManager: FragmentManager,
        fragmentTag: String,
        navGraphId: Int,
        containerId: Int
    ): NavHostFragment {
        val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
        existingFragment?.let { return it }

        val navHostFragment = NavHostFragment.create(navGraphId)
        fragmentManager.beginTransaction()
            .add(containerId, navHostFragment, fragmentTag)
            .commitNow()
        return navHostFragment
    }
}
