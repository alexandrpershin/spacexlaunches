package com.alexandrpershin.spacexlaunches.ui.fragment


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.ui.adapter.RocketLaunchesAdapter
import com.alexandrpershin.spacexlaunches.ui.activity.RocketLaunchesViewModel
import com.alexandrpershin.spacexlaunches.ui.state.ScreenState
import com.alexandrpershin.spacexlaunches.utils.stopRefreshing
import kotlinx.android.synthetic.main.fragment_rocket_launches.*

class RocketLaunchesFragment : BaseFragment() {

    private lateinit var mViewModel: RocketLaunchesViewModel

    private lateinit var adapter: RocketLaunchesAdapter

    override fun getLayoutId(): Int = R.layout.fragment_rocket_launches

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.app_name)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel =
            ViewModelProvider(requireActivity()).get(RocketLaunchesViewModel::class.java) //get ViewModel from MainActivity
        mViewModel.refreshDataAction()

        setupObservers()
        setupRecyclerView()
        setupSwipeToRefreshLayout()
    }

    private fun setupSwipeToRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            mViewModel.refreshDataAction.invoke()
            swipeRefreshLayout.stopRefreshing()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter =
            RocketLaunchesAdapter(requireContext())
        recyclerView.adapter = adapter

        adapter.onItemClick = { rocketLaunchId ->
            showRocketDetailsFragment(rocketLaunchId)
        }
    }

    private fun showRocketDetailsFragment(rocketLaunchId: Long) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, RocketDetailsFragment.newInstance(rocketLaunchId))
            .addToBackStack(RocketDetailsFragment.TAG)
            .commit()
    }

    private fun setupObservers() {
        mViewModel.rocketLaunchesLiveData.observe(viewLifecycleOwner, Observer { newData ->
            adapter.submitData(newData)
        })

        mViewModel.statusLiveData.observe(viewLifecycleOwner, Observer { status ->

            when (status) {
                is ScreenState.Success -> {
                    hideProgressBar()
                }

                is ScreenState.InternetError -> {
                    showSnackBar(getString(R.string.label_no_internet))
                    hideProgressBar()
                }

                is ScreenState.Error -> {
                    hideProgressBar()
                    super.handleError(status)
                }

                is ScreenState.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter_launches, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_tbd_disabled -> {
                mViewModel.filterRocketLaunchesByTbd(false)
            }
            R.id.item_tbd_enabled -> {
                mViewModel.filterRocketLaunchesByTbd(true)
            }
            R.id.item_all -> {
                mViewModel.fetchAllRocketLaunches()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    companion object {
        val TAG = RocketLaunchesFragment::class.java.simpleName
    }

}
