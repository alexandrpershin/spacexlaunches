package com.alexandrpershin.spacexlaunches.ui.fragment


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alexandrpershin.spacexlaunches.R
import com.alexandrpershin.spacexlaunches.model.RocketLaunchDbEntity
import com.alexandrpershin.spacexlaunches.model.RocketLaunchUtils
import com.alexandrpershin.spacexlaunches.ui.activity.RocketLaunchesViewModel
import com.alexandrpershin.spacexlaunches.ui.state.ScreenState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_rocket_details.*

class RocketDetailsFragment : BaseFragment() {

    private lateinit var mViewModel: RocketLaunchesViewModel

    private var rocketLaunchId = -1L

    override fun getLayoutId(): Int = R.layout.fragment_rocket_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rocketLaunchId = arguments!!.getLong(ROCKET_LAUNCH_ID, -1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.title_rocket_details)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel =
            ViewModelProvider(requireActivity()).get(RocketLaunchesViewModel::class.java) //get ViewModel from MainActivity

        setupObservers()
    }

    private fun setupObservers() {
        mViewModel.getRocketDetails(rocketLaunchId).observe(viewLifecycleOwner, Observer { rocket ->
            updateUi(rocket)
        })

        mViewModel.statusLiveData.observe(viewLifecycleOwner, Observer { status ->

            when (status) {
                is ScreenState.Success -> {
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

    private fun updateUi(rocketLaunch: RocketLaunchDbEntity) {
        rocketNameTv.text = rocketLaunch.rocket?.rocketName
        launchSuccessTv.text = RocketLaunchUtils.getStatus(requireContext(), rocketLaunch.launchSuccess)
        rocketDescriptionTv.text = RocketLaunchUtils.getDetails(requireContext(), rocketLaunch.details)
        rocketTypeTv.text = rocketLaunch.rocket?.rocketType

        Glide.with(requireContext())
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_jetpack_rocket))
            .load(rocketLaunch.imageUrl).into(rocketIconIv)
    }

    companion object {
        val TAG = RocketDetailsFragment::class.java.simpleName
        const val ROCKET_LAUNCH_ID = "rocket_launch_id"

        fun newInstance(rocketLaunchId: Long) = RocketDetailsFragment().apply {
            val bundle = Bundle()
            bundle.putLong(ROCKET_LAUNCH_ID, rocketLaunchId)
            arguments = bundle
        }


    }

}
