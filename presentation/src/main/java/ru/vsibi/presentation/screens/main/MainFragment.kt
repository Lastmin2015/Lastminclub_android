package ru.vsibi.presentation.screens.main

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import ru.vsibi.presentation.R
import ru.vsibi.presentation.base.BaseFragment
import ru.vsibi.presentation.databinding.FragmentMainBinding
import ru.vsibi.presentation.helpers.Lastmin.gone
import ru.vsibi.presentation.helpers.Lastmin.visible

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate, R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        router.mainFragmentInstance = this
    }

    override fun FragmentMainBinding.initViews() {
        navHostFragment = childFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        router.childNavController = navHostFragment?.navController
        router.childNavHostFragment = navHostFragment
        router.parentNavController = findNavController()
        navHostFragment?.navController?.let { NavigationUI.setupWithNavController(binding.bottomNavigationView, it) }
        navHostFragment?.navController?.addOnDestinationChangedListener { _, destination, _ ->
            log("destination " + destination.label)
            when (destination.id) {
                R.id.travellersFragment -> hideNavigation()
                R.id.dateRangeDialog -> hideNavigation()
                R.id.tourInfoFragment -> hideNavigation()
                R.id.flightsListFragment -> hideNavigation()
                R.id.flightsInfoFragment -> hideNavigation()
                R.id.photoViewerFragment -> {
                    hideNavigation()
                    hideToolbar()
                }
                else -> showNavigation()
            }
        }
    }

    override fun initFragment() {
        if (viewModel.isAuth) {
            binding.bottomNavigationView.menu.getItem(3).title = getString(R.string.profile)
        } else {
            binding.bottomNavigationView.menu.getItem(3).title = getString(R.string.sign_in)

            binding.bottomNavigationView.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.profileFragment->{
                        router.navigateToLogin()
                    }
                    else -> {
                        navHostFragment?.navController?.let { it1 -> NavigationUI.onNavDestinationSelected(it, it1) }
                    }
                }
                return@setOnNavigationItemSelectedListener false
            }
        }
        viewModel.setupAccess()
    }

    fun showNavigation() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideNavigation() {
        binding.bottomNavigationView.visibility = View.GONE
        binding.bottomNavigationView.isShown
    }

    fun showToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    fun hideToolbar() {
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    fun onStartLoad() {
        binding.include.progress.visible()
    }

    fun onEndLoad() {
        binding.include.progress.gone()
    }

    fun clearAuth() {
        viewModel.clearAuth()
    }

}

